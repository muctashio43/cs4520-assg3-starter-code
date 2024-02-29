package com.cs4520.assignment3.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.cs4520.assignment3.databinding.MvpCalculatorBinding
import com.cs4520.assignment3.presenters.CalculatorPresenter
import com.cs4520.assignment3.views.CalculatorView

class MVPFragment : Fragment(), CalculatorView {

    private lateinit var binding: MvpCalculatorBinding;
    private lateinit var presenter: CalculatorPresenter;

    val field1: Int? get() {
        val str = binding.field1.text.toString();
        if (str == "") {
            return null;
        }
        else {
            return str.toInt();
        }
    }

    val field2: Int? get() {
        val str = binding.field2.text.toString();
        if (str == "") {
            return null;
        }
        else {
            return str.toInt();
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MvpCalculatorBinding.inflate(inflater, container, false);
        presenter = CalculatorPresenter(this);
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.add.setOnClickListener {
            presenter.add(field1, field2);
        };

        binding.subtract.setOnClickListener {
            presenter.subtract(field1, field2);
        };

        binding.multiply.setOnClickListener {
            presenter.multiply(field1, field2);
        };

        binding.divide.setOnClickListener {
            presenter.divide(field1, field2);
        };

    }
    override fun clearFields() {
        binding.field1.text.clear();
        binding.field2.text.clear();
    }

    override fun setResult(n: Double?) {
        if (n == null) {
            binding.result.text = ""
        }
        else {
            binding.result.text = "Result: " + n.toString();
        }
    }

    override fun sendToastMessage(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }


}