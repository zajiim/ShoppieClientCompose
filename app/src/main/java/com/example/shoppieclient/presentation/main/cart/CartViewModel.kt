package com.example.shoppieclient.presentation.main.cart

import androidx.lifecycle.ViewModel
import com.example.shoppieclient.domain.models.ShoppieItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor() : ViewModel() {

    val myCartItems: List<ShoppieItem> = listOf(
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
            id = "2",
            images = listOf("https://static.nike.com/a/images/t_PDP_1280_v1/f_auto,q_auto:eco/99486859-0ff3-46b4-949b-2d16af2ad421/custom-nike-dunk-high-by-you-shoes.png"),
            category = "NEW ARRIVAL",
            name = "Nike",
            brand = "Nike",
            price = 34,
            description = "",
            quantity = 5
        ),
    )
}