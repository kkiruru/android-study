package com.kkiruru.navigation.ui.store

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kkiruru.navigation.databinding.FragmentStoreBinding

class StoreFragment : Fragment() {

    private var _binding: FragmentStoreBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val myViewModel =
            ViewModelProvider(this).get(StoreViewModel::class.java)

        _binding = FragmentStoreBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDashboard
        myViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        Log.e("StoreFragment", "onDestroyView")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e("StoreFragment", "onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("StoreFragment", "onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.e("StoreFragment", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e("StoreFragment", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e("StoreFragment", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e("StoreFragment", "onStop")
    }

    override fun onDetach() {
        super.onDetach()
        Log.e("StoreFragment", "onDetach")
    }
}