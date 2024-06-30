package com.kkiruru.navigation.ui.laundry

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kkiruru.navigation.databinding.FragmentLaundryBinding

class LaundryFragment : Fragment() {

    private var _binding: FragmentLaundryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.e("LaundryFragment", "onCreateView")

        val myViewModel =
            ViewModelProvider(this).get(LaundryViewModel::class.java)

        _binding = FragmentLaundryBinding.inflate(inflater, container, false)
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
        Log.e("LaundryFragment", "onDestroyView")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e("LaundryFragment", "onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("LaundryFragment", "onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.e("LaundryFragment", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e("LaundryFragment", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e("LaundryFragment", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e("LaundryFragment", "onStop")
    }

    override fun onDetach() {
        super.onDetach()
        Log.e("LaundryFragment", "onDetach")
    }

}