package com.cs4520.assignment3.fragments

import CalculatorViewModel
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cs4520.assignment3.databinding.MvvmCalculatorBinding

class MVVMFragment : Fragment() {

    private lateinit var binding: MvvmCalculatorBinding;
    private lateinit var viewModel: CalculatorViewModel;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MvvmCalculatorBinding.inflate(inflater, container, false);
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(CalculatorViewModel::class.java)

        setListeners();
        setObservers();
    }

    fun setListeners() {
        binding.add.setOnClickListener {
            viewModel.add();
        };

        binding.subtract.setOnClickListener {
            viewModel.subtract();
        };

        binding.multiply.setOnClickListener {
            viewModel.multiply();
        };

        binding.divide.setOnClickListener {
            viewModel.divide();
        };

        binding.field1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.setField1(s.toString());
            }

            override fun afterTextChanged(s: Editable?) {
            }
        });

        binding.field2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.setField2(s.toString());
            }

            override fun afterTextChanged(s: Editable?) {
            }
        });
    }

    fun setObservers() {
        viewModel.field1.observe(viewLifecycleOwner, Observer { n ->
            val newValue = if (n == null) "" else n.toString();
            if (binding.field1.text.toString() != newValue) {
                binding.field1.setText(newValue)
            }
        });

        viewModel.field2.observe(viewLifecycleOwner, Observer { n ->
            val newValue = if (n == null) "" else n.toString();
            if (binding.field2.text.toString() != newValue) {
                binding.field2.setText(newValue)
            }
        });

        viewModel.result.observe(viewLifecycleOwner, Observer { n ->
            binding.result.text = (if (n == null) "" else "Result: " + n.toString())
        })

        // Used as a way to avoid the toast from rerunning when rotating screen.
        var has_run_error_init = false;
        viewModel.error.observe(viewLifecycleOwner, Observer { err ->
            if (has_run_error_init) {
                Toast.makeText(context, err, Toast.LENGTH_LONG).show()
            }
            has_run_error_init = true;
        })
    }

}