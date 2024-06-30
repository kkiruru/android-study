package com.kkiruru.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kkiruru.navigation.databinding.ActivityMainBinding
import com.kkiruru.navigation.ui.address.AddressActivity


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.moveToHome.setOnClickListener {
            NavigationActivity.startActivity(this, initialTab = NavigationItem.Home)
        }

        binding.moveToLaundry.setOnClickListener {
            NavigationActivity.startActivity(this, initialTab = NavigationItem.Laundry)
        }

        binding.moveToAddress.setOnClickListener {
            AddressActivity.startActivity(this)
        }

        binding.moveToAddressOnlySearch.setOnClickListener {
            AddressActivity.startActivity(this, searchOnly = true)
        }

        binding.moveToAddressSetting.setOnClickListener {
            AddressActivity.startSettingActivity(this)
        }

    }
}
