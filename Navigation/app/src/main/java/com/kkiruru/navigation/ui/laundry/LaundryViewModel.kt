package com.kkiruru.navigation.ui.laundry

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LaundryViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Laundry Fragment"
    }
    val text: LiveData<String> = _text
}