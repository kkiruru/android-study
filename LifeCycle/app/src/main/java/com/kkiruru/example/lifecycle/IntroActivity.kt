package com.kkiruru.example.lifecycle

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("IntroActivity", "onCreate")
        enableEdgeToEdge()
        setContentView(R.layout.activity_intro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        lifecycleScope.launch {
            delay(500L)
            MainActivity.startMainWithClearTop(this@IntroActivity, "fromIntro")
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        Log.e("IntroActivity", "onResume")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.e("IntroActivity", "onSaveInstanceState ${outState}")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.e("IntroActivity", "onRestoreInstanceState ${savedInstanceState}")
    }

    override fun onPause() {
        super.onPause()
        Log.e("IntroActivity", "onPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("IntroActivity", "onDestroy")
    }
}