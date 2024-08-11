package com.example.shoppieclient.data.repository

import com.example.shoppieclient.domain.main.use_cases.DataStoreUseCases
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CartRepository @Inject constructor(
    private val dataStoreUseCases: DataStoreUseCases
) {
    private val _cartCount = MutableStateFlow(0)
    val cartCount: StateFlow<Int> = _cartCount.asStateFlow()

    init {
        CoroutineScope(Dispatchers.IO).launch {
            dataStoreUseCases.readCartCountUseCase().collect { count ->
                _cartCount.value = count
            }
        }
    }

    suspend fun updateCartCount(newCount: Int) {
        dataStoreUseCases.saveCartCountUseCase(newCount)
        _cartCount.value = newCount
    }
}