package com.example.shoppieclient.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class UserDto(
    @SerialName("address")
    val address: String,
    @SerialName("email")
    val email: String,
    @SerialName("_id")
    val _id: String,
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
    val cart: List<CartItemDto>
)

@Serializable
data class CartItemDto(
    @SerialName("product")
    val product: ProductDto,
    @SerialName("quantity")
    val quantity: Int
)

@Serializable
data class ProductDto(
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