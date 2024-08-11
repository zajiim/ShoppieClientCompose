package com.example.shoppieclient.domain.auth.models.cart

data class Cart(
    val cartDetails: List<CartDetails>,
    val currentPage: Int,
    val status: String,
    val totalCartItems: Int,
    val totalPages: Int
)

data class CartDetails(
    val id: String,
    val product: Products,
    val quantity: Int
)

data class Products(
    val brand: String,
    val category: String,
    val description: String,
    val id: String,
    val images: List<String>?,
    val name: String,
    val price: Int,
    val productId: String,
    val quantity: Int,
    val v: Int
)