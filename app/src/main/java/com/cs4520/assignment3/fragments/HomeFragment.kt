package com.cs4520.assignment3.fragments
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.cs4520.assignment3.R
import com.cs4520.assignment3.databinding.HomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: HomeBinding;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeBinding.inflate(inflater, container, false);
        return binding.root;
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mvp.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_MVPFragment)
        };

        binding.mvvm.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_MVVMFragment)
        }

    }


}