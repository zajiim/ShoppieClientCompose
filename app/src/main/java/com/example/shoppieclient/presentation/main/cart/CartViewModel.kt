package com.example.shoppieclient.presentation.main.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.shoppieclient.domain.auth.models.cart.Products
import com.example.shoppieclient.domain.auth.repository.ShoppieRepo
import com.example.shoppieclient.domain.auth.use_cases.cart.GetCartUseCase
import com.example.shoppieclient.domain.main.use_cases.DataStoreUseCases
import com.example.shoppieclient.domain.models.ShoppieItem
import com.example.shoppieclient.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val shoppieRepo: ShoppieRepo,
    private val dataStoreUseCases: DataStoreUseCases,
    private val getCartUseCase: GetCartUseCase
) : ViewModel() {
    private val _cartItems = MutableStateFlow<PagingData<Products>>(PagingData.empty())
    val cartItems = _cartItems.asStateFlow()

    init {
        fetchCartItems()
    }

    private fun fetchCartItems() = viewModelScope.launch {
        val token = dataStoreUseCases.readTokenUseCase().firstOrNull()

        if (token != null) {
            getCartUseCase(
                token = token,
                page = Constants.INITIAL_PAGE_INDEX,
                limit = Constants.PER_PAGE_ITEMS
            ).collect { cartsPagingData ->
                _cartItems.value = cartsPagingData
            }
        } else {
            _cartItems.value = PagingData.empty()
        }
    }

}