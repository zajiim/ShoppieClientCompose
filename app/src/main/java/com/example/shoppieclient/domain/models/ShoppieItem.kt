package com.example.shoppieclient.domain.models


data class ShoppieItem(
    val id: String,
    val productId: String,
    val name: String?,
    val brand: String?,
    val description: String?,
    val images: List<String>?,
    val category: String?,
    val price: Int?,
    val quantity: Int?
)
