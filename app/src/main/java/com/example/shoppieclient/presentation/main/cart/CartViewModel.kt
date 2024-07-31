package com.example.shoppieclient.presentation.main.cart

import androidx.lifecycle.ViewModel
import com.example.shoppieclient.domain.models.ShoppieItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(): ViewModel(){

    val myCartItems: List<ShoppieItem> = listOf(
        ShoppieItem(
            id = "1",
            imageUrl = "https://m.media-amazon.com/images/I/614aiM56siL._SL1500_.jpg",
            category = "BEST SELLING",
            productName = "Nike",
            price = "23",
            description = ""
        ),

        ShoppieItem(
            id = "2",
            imageUrl = "https://static.nike.com/a/images/t_PDP_1280_v1/f_auto,q_auto:eco/99486859-0ff3-46b4-949b-2d16af2ad421/custom-nike-dunk-high-by-you-shoes.png",
            category = "NEW ARRIVAL",
            productName = "Puma",
            price = "23",
            description = ""
        ),

        ShoppieItem(
            id = "3",
            imageUrl = "https://5.imimg.com/data5/SELLER/Default/2022/11/YV/ZF/YO/116453489/white-casual-shoes-for-men.jpg",
            category = "TRENDING NOW",
            productName = "Woodland",
            price = "23",
            description = ""
        ),

        ShoppieItem(
            id = "4",
            imageUrl = "https://5.imimg.com/data5/SELLER/Default/2022/11/YV/ZF/YO/116453489/white-casual-shoes-for-men.jpg",
            category = "NEW ARRIVAL",
            productName = "Adidas",
            price = "23",
            description = ""
        ),

        )
}