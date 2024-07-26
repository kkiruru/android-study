package com.kkiruru.example.lifecycle

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.kkiruru.example.lifecycle.databinding.ActivityBBinding

class BActivity : AppCompatActivity() {

    private var _binding: ActivityBBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("BActivity", "onCreate")
        enableEdgeToEdge()
        _binding = ActivityBBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.moveToSecond.setOnClickListener {
            startActivity(
                Intent(this@BActivity, AActivity::class.java)
            )
        }
    }

    override fun onPause() {
        super.onPause()
        Log.e("BActivity", "onPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("BActivity", "onDestroy")
    }


    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        Log.e("BActivity", "onNewIntent")
    }

    override fun onStart() {
        super.onStart()
        Log.e("BActivity", "onStart")
    }

    override fun onRestart() {
        super.onRestart()
        Log.e("BActivity", "onRestart")
    }

    override fun onResume() {
        super.onResume()
        Log.e("BActivity", "onResume")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.e("BActivity", "onSaveInstanceState ${outState}")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.e("BActivity", "onRestoreInstanceState")
    }
}