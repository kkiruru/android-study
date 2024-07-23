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
import com.kkiruru.example.lifecycle.databinding.FragmentABinding
import com.kkiruru.example.lifecycle.ui.BActivity

class AFragment : Fragment() {

    private var _binding: FragmentABinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = AFragment()
    }

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("BFragment", "onCreate")

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.e("BFragment", "onCreateView")
        _binding = FragmentABinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.moveToMain.setOnClickListener {
            MainActivity.startMain(requireContext(), "fromSecond")
        }

        binding.moveToMainClearTop.setOnClickListener {
            MainActivity.startMainWithClearTop(requireContext(), "fromSecond")
        }

        binding.moveToB.setOnClickListener {
            startActivity(
                Intent(requireContext(), BActivity::class.java)
            )
        }

        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        Log.e("BFragment", "onDestroyView")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e("BFragment", "onAttach")
    }

    override fun onStart() {
        super.onStart()
        Log.e("BFragment", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e("BFragment", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e("BFragment", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e("BFragment", "onStop")
    }

    override fun onDetach() {
        super.onDetach()
        Log.e("BFragment", "onDetach")
    }
}