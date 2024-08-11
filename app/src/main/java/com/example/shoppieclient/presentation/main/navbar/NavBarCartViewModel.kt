package com.example.shoppieclient.presentation.main.navbar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppieclient.domain.auth.models.home.CartCount
import com.example.shoppieclient.domain.auth.use_cases.main.navbar.GetCartCountUseCase
import com.example.shoppieclient.domain.main.use_cases.DataStoreUseCases
import com.example.shoppieclient.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NavBarCartViewModel @Inject constructor(
    private val dataStoreUseCases: DataStoreUseCases,
    private val getCartCountUseCase: GetCartCountUseCase
) : ViewModel() {
//    private val _cartCount = MutableStateFlow<Resource<CartCount>>(Resource.Loading(true))
//    val cartCount = _cartCount.asStateFlow()

    private val _cartCount = MutableStateFlow(0)
    val cartCount = _cartCount.asStateFlow()

    init {
        fetchCartCount()
    }

    fun fetchCartCount() = viewModelScope.launch {
        val token = dataStoreUseCases.readTokenUseCase().firstOrNull()
        if (!token.isNullOrEmpty()) {
            getCartCount(token)
        } else {
            _cartCount.value = 0
        }

    }

    private fun getCartCount(token: String) = viewModelScope.launch {
        getCartCountUseCase(token).collect { result ->
            _cartCount.value = result.data?.cartCount ?: 0
//            when (result) {
//                is Resource.Success -> {
//                    _cartCount.value = result.data?.cartCount ?: 0
//                }
//                is Resource.Error -> {
//                    _cartCount.value = 0
//                }
//                is Resource.Loading -> {
//
//                }
//            }
        }
    }


}