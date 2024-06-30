package com.kkiruru.navigation.ui.address

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel


class AddressViewModel (
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val isSearchOnlyMode = savedStateHandle.getStateFlow(KEY_SEARCH_ONLY_MODE, false)

    fun initSearchMode(isSearchOnly: Boolean) {
        savedStateHandle[KEY_SEARCH_ONLY_MODE] = isSearchOnly
    }

    override fun onCleared() {
        savedStateHandle.remove<Boolean>(KEY_SEARCH_ONLY_MODE)
        super.onCleared()
    }

    companion object {
        const val KEY_SEARCH_ONLY_MODE = "KEY_SEARCH_ONLY_MODE"
    }
}