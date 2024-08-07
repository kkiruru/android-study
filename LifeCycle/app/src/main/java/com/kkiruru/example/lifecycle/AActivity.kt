package com.kkiruru.example.lifecycle

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class AActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("AActivity", "onCreate : ${savedInstanceState}")
        setContentView(R.layout.activity_a)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, AFragment.newInstance())
                .commitNow()
        }
    }

    override fun onPause() {
        super.onPause()
        Log.e("AActivity", "onPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("AActivity", "onDestroy")
    }


    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, AActivity::class.java).apply {
            }
            context.startActivity(intent)
        }
    }
}