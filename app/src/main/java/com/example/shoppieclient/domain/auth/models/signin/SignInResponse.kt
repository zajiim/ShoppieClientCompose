package com.example.shoppieclient.domain.auth.models.signin


data class User(
    val token: String,
    val id: String,
    val name: String,
    val email: String,
    val address: String,
    val type: String,
    val cart: List<CartItem>
)

data class CartItem(
    val product: Product,
    val quantity: Int
)

data class Product(
    val productId: String,
    val name: String,
    val brand: String,
    val description: String,
    val quantity: Int,
    val price: Double,
    val category: String,
    val images: List<String>
)