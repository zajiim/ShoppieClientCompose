package com.example.shoppieclient.presentation.main.navbar

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppieclient.data.repository.CartRepository
import com.example.shoppieclient.domain.main.use_cases.DataStoreUseCases
import com.example.shoppieclient.domain.main.use_cases.ReadCartCountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NavBarCartViewModel @Inject constructor(
    cartRepository: CartRepository
) : ViewModel() {

    val cartCount = cartRepository.cartCount
}