package com.example.shoppieclient.domain.auth.models.signin

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class SignInResponse(
    @SerialName("address")
    val address: String,
    @SerialName("email")
    val email: String,
    @SerialName("_id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("password")
    val password: String,
    @SerialName("token")
    val token: String,
    @SerialName("type")
    val type: String,
    @SerialName("__v")
    val v: Int,
    @SerialName("cart")
    val cart: List<CartItem>
)

@Serializable
data class CartItem(
    @SerialName("product")
    val product: Product,
    @SerialName("quantity")
    val quantity: Int
)

@Serializable
data class Product(
    @SerialName("productId")
    val productId: String,
    @SerialName("name")
    val name: String,
    @SerialName("brand")
    val brand: String,
    @SerialName("description")
    val description: String,
    @SerialName("quantity")
    val quantity: Int,
    @SerialName("price")
    val price: Double,
    @SerialName("category")
    val category: String,
    @SerialName("images")
    val images: List<String>
)