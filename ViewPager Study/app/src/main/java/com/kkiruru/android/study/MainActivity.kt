package com.kkiruru.android.study

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kkiruru.android.study.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.main1.setOnClickListener {
            startActivity(
                Intent(this, MainActivity1::class.java)
            )
        }
        binding.main5.setOnClickListener {
            startActivity(
                Intent(this, MainActivity5::class.java)
            )
        }
    }
}