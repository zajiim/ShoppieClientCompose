package com.example.shoppieclient.domain.models

data class ShoppieItem(
    val id: String?,
    val name: String?,
    val description: String?,
    val images: List<String>?,
    val category: String?,
    val price: Int?,
    val quantity: Int?
)
