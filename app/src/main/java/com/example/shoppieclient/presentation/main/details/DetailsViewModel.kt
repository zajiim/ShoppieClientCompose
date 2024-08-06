package com.example.shoppieclient.presentation.main.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppieclient.domain.auth.use_cases.details.GetProductDetailsUseCase
import com.example.shoppieclient.domain.main.use_cases.DataStoreUseCases
import com.example.shoppieclient.domain.models.ShoppieItem
import com.example.shoppieclient.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getProductDetailsUseCase: GetProductDetailsUseCase,
    private val dataStoreUseCase: DataStoreUseCases,
): ViewModel() {

    private val _productDetails = MutableStateFlow<Resource<ShoppieItem>>(Resource.Loading(true))
    val productDetails = _productDetails.asStateFlow()





    fun fetchProductDetails(id: String) = viewModelScope.launch {
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

}