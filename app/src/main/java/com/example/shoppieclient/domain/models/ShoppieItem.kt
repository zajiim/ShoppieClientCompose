package com.example.shoppieclient.domain.models

data class ShoppieItem(
    val id: String,
    val imageUrl: String,
    val category: String,
    val productName: String,
    val price: String,
    val description: String
)
