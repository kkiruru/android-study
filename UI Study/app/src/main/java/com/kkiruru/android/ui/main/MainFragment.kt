package com.kkiruru.android.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.kkiruru.android.ui.R

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        var value =  inflater.inflate(R.layout.fragment_main, container, false)

        var message = value?.findViewById<TextView>(R.id.message)

        message?.text = foo("가나다", R.color.purple_200).append(foo("다라바", R.color.teal_700))

        return value
    }


    fun foo(string: String, colorId: Int) : SpannableStringBuilder {
        var spannableStringBuilder = SpannableStringBuilder(string)
        spannableStringBuilder.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(requireActivity().baseContext, colorId)),
            0,
            string.length,
            Spanned.SPAN_EXCLUSIVE_INCLUSIVE
        )
        return spannableStringBuilder
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

}