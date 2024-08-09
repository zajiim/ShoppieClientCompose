package com.example.shoppieclient.presentation.auth.signin

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor():ViewModel(){
    private val _userCartItemCount = MutableStateFlow(0)
    val userCartItemCount: StateFlow<Int> = _userCartItemCount

    fun updateCartCount(count: Int) {
        _userCartItemCount.value = count
    }

}