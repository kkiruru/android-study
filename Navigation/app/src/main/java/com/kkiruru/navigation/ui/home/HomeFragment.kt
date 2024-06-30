package com.kkiruru.navigation.ui.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kkiruru.navigation.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.e("HomeFragment", "onCreateView")
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        Log.e("HomeFragment", "onDestroyView")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e("HomeFragment", "onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("HomeFragment", "onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.e("HomeFragment", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e("HomeFragment", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e("HomeFragment", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e("HomeFragment", "onStop")
    }

    override fun onDetach() {
        super.onDetach()
        Log.e("HomeFragment", "onDetach")
    }
}