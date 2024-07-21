package com.kkiruru.example.lifecycle

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.kkiruru.example.lifecycle.ui.main.SecondFragment

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("SecondActivity", "onCreate : ${savedInstanceState}")
        setContentView(R.layout.activity_second)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, SecondFragment.newInstance())
                .commitNow()
        }
    }

    override fun onPause() {
        super.onPause()
        Log.e("SecondActivity", "onPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("SecondActivity", "onDestroy")
    }
}