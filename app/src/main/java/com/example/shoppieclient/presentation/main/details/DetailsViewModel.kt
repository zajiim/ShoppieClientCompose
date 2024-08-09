package com.example.shoppieclient.presentation.main.details

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppieclient.domain.auth.models.signin.User
import com.example.shoppieclient.domain.auth.use_cases.details.AddToCartUseCases
import com.example.shoppieclient.domain.auth.use_cases.details.GetProductDetailsUseCase
import com.example.shoppieclient.domain.main.use_cases.DataStoreUseCases
import com.example.shoppieclient.domain.models.ShoppieItem
import com.example.shoppieclient.presentation.main.cart.CartViewModel
import com.example.shoppieclient.presentation.main.navbar.NavBarCartViewModel
import com.example.shoppieclient.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getProductDetailsUseCase: GetProductDetailsUseCase,
    private val dataStoreUseCase: DataStoreUseCases,
    private val addToCartUseCases: AddToCartUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _productDetails = MutableStateFlow<Resource<ShoppieItem>>(Resource.Loading(true))
    val productDetails = _productDetails.asStateFlow()

    private val _userDetails = MutableStateFlow<Resource<User>>(Resource.Loading(true))
    val userDetails = _userDetails.asStateFlow()


    init {
        val productId = savedStateHandle.get<String>("itemId")
        productId?.let { fetchProductDetails(it) }
    }



    private fun fetchProductDetails(id: String) = viewModelScope.launch {
        val token = dataStoreUseCase.readTokenUseCase().firstOrNull()
        if (token != null) {
            getProductDetails(token, id)
        } else {
            _productDetails.value = Resource.Error("No valid token found")
        }
    }

    private fun getProductDetails(token: String, id: String) = viewModelScope.launch {
        getProductDetailsUseCase(token, id).collect { result ->
            _productDetails.value = result
        }
    }

    fun onAddToCartClick(id: String) = viewModelScope.launch {
        val token = dataStoreUseCase.readTokenUseCase().firstOrNull()
        if (token != null) {
            addToCart(id, token)
        } else {
            _productDetails.value = Resource.Error("No valid token found")
        }
    }

    private fun addToCart(id: String, token: String) = viewModelScope.launch {
            addToCartUseCases(token, id).collect { result ->
                _userDetails.value = result
                if (result is Resource.Success) {
                    result.data?.let { user ->
                        dataStoreUseCase.saveCartCountUseCase(user.cart.size)
                    }
                }
            }

    }

}