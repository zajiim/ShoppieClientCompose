package com.example.shoppieclient.presentation.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(): ViewModel() {

    fun searchItems(query: String) {
        viewModelScope.launch {

        }
    }

    fun onChipSelected(brandName: String) = viewModelScope.launch {  }
}