package com.kkiruru.android.study

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, BlankFragment4())
            .commitNow()
    }
}