package com.kkiruru.example.lifecycle.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kkiruru.example.lifecycle.MainActivity
import com.kkiruru.example.lifecycle.SecondActivity
import com.kkiruru.example.lifecycle.databinding.FragmentSecondBinding
import com.kkiruru.example.lifecycle.ui.ThirdActivity

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = SecondFragment()
    }

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("SecondFragment", "onCreate")

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.e("SecondFragment", "onCreateView")
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.moveToMain.setOnClickListener {
            MainActivity.startMain(requireContext(), "fromSecond")
        }

        binding.moveToMainClearTop.setOnClickListener {
            MainActivity.startMainWithClearTop(requireContext(), "fromSecond")
        }

        binding.moveToThird.setOnClickListener {
            startActivity(
                Intent(requireContext(), ThirdActivity::class.java)
            )
        }

        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        Log.e("SecondFragment", "onDestroyView")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e("SecondFragment", "onAttach")
    }

    override fun onStart() {
        super.onStart()
        Log.e("SecondFragment", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e("SecondFragment", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e("SecondFragment", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e("SecondFragment", "onStop")
    }

    override fun onDetach() {
        super.onDetach()
        Log.e("SecondFragment", "onDetach")
    }
}