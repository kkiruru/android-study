package com.kkiruru.android.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kkiruru.android.ui.main.MainFragment

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }

    companion object {
        private const val TAG = "MainActivity2"

        fun startActivity(context: Context) {
            val intent = Intent(context, MainActivity2::class.java)
            context.startActivity(intent)
        }
    }
}