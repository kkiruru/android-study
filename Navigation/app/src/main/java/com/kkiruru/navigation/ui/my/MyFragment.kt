package com.kkiruru.navigation.ui.my

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kkiruru.navigation.NavigationActivity
import com.kkiruru.navigation.databinding.FragmentMyBinding

class MyFragment : Fragment() {

    private var _binding: FragmentMyBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val myViewModel =
            ViewModelProvider(this).get(MyViewModel::class.java)

        _binding = FragmentMyBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDashboard
        myViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        binding.textDashboard.setOnClickListener {
            NavigationActivity.startActivity(requireContext())
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        Log.e("MyFragment", "onDestroyView")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e("MyFragment", "onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("MyFragment", "onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.e("MyFragment", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e("MyFragment", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e("MyFragment", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e("MyFragment", "onStop")
    }

    override fun onDetach() {
        super.onDetach()
        Log.e("MyFragment", "onDetach")
    }
}