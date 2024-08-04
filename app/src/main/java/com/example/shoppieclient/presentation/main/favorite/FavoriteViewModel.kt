package com.example.shoppieclient.presentation.main.favorite

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.shoppieclient.domain.auth.repository.ShoppieRepo
import com.example.shoppieclient.domain.models.ShoppieItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repository: ShoppieRepo
): ViewModel() {

    val favShoppieItems: List<ShoppieItem> = listOf(
        ShoppieItem(
            id = "1",
            images = listOf("https://m.media-amazon.com/images/I/614aiM56siL._SL1500_.jpg"),
            category = "BEST SELLING",
            name = "Nike",
            brand = "Nike",
            price = 34,
            description = "",
            quantity = 5
        ),

        ShoppieItem(
            id = "1",
            images = listOf("https://m.media-amazon.com/images/I/614aiM56siL._SL1500_.jpg"),
            category = "BEST SELLING",
            name = "Nike",
            brand = "Nike",
            price = 34,
            description = "",
            quantity = 5
        ),
    )
}