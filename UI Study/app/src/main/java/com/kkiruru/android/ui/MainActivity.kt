package com.kkiruru.android.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kkiruru.android.ui.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.main1.setOnClickListener {
            startActivity(
                Intent(this, MainActivity2::class.java)
            )
        }

        binding.dialog.setOnClickListener {
            val itemListDialog = CustomBottomSheetDialogFragment.newInstance(5)
            itemListDialog.show(
                    supportFragmentManager,
                CustomBottomSheetDialogFragment.TAG)
        }

    }
}