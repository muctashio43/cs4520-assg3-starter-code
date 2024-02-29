import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cs4520.assignment3.models.OperationResult

class CalculatorViewModel : ViewModel() {
    private val _field1 = MutableLiveData<Int?>()
    val field1: LiveData<Int?> = _field1

    private val _field2 = MutableLiveData<Int?>()
    val field2: LiveData<Int?> = _field2

    private val _result = MutableLiveData<Double?>()
    val result: LiveData<Double?> = _result

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun setField1(field: String) {
        _field1.value = if (field == "") null else field.toInt() ;
    }

    fun setField2(field: String) {
        _field2.value = if (field == "") null else field.toInt() ;
    }
    fun add() {
        conductOperation(field1.value, field2.value) { n1, n2 -> OperationResult((n1 + n2).toDouble(), null) }
    }

    fun subtract() {
        conductOperation(field1.value, field2.value) { n1, n2 -> OperationResult((n1 - n2).toDouble(), null) }
    }

    fun multiply() {
        conductOperation(field1.value, field2.value) { n1, n2 -> OperationResult((n1 * n2).toDouble(), null) }
    }

    fun divide() {
        conductOperation(field1.value, field2.value) { n1, n2 ->
            if (n2 == 0)
                OperationResult(null, "Divide by 0 error")
            else
                OperationResult((n1 * n2).toDouble(), null)
        }
    }

    private fun conductOperation(field1: Int?, field2: Int?, f: (n1: Int, n2: Int) -> OperationResult) {
        _field1.value = null;
        _field2.value = null;

        if (field1 == null || field2 == null) {
            _error.value = "Make sure both fields have input numbers"
            return;
        }

        val r = f(field1, field2);
        if (r.output != null) {
            _result.value = r.output
        }
        else {
            val err = r.err;
            if (err != null) {
                _error.value = err as String;
            }
            else {
                _error.value = "Unknown invalid mathematical operation"
            }
        }
    }
}