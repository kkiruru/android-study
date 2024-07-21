package com.kkiruru.example.lifecycle.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.kkiruru.example.lifecycle.R
import com.kkiruru.example.lifecycle.SecondActivity
import com.kkiruru.example.lifecycle.databinding.ActivityThirdBinding
import com.kkiruru.example.lifecycle.databinding.FragmentSecondBinding

class ThirdActivity : AppCompatActivity() {

    private var _binding: ActivityThirdBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("ThirdActivity", "onCreate")
        enableEdgeToEdge()
        _binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.moveToSecond.setOnClickListener {
            startActivity(
                Intent(this@ThirdActivity, SecondActivity::class.java)
            )
        }
    }

    override fun onPause() {
        super.onPause()
        Log.e("ThirdActivity", "onPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("ThirdActivity", "onDestroy")
    }
}