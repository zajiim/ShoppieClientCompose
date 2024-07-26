package com.example.shoppieclient.presentation.main.home

import com.example.shoppieclient.R

data class ShoeItem(
    val id: Int,
    val image: Int,
    val name: String,
    val price: Double,
)


val shoesList = listOf(
    ShoeItem(1, R.drawable.ic_shoe, "Nike Jordan", 493.00),
    ShoeItem(2, R.drawable.ic_shoe2, "Adidas Superstar", 89.99),
    ShoeItem(3, R.drawable.ic_shoe3, "Puma RS-X", 110.00),
    ShoeItem(3, R.drawable.ic_shoe, "Reebok RS-X", 113.00),
)
