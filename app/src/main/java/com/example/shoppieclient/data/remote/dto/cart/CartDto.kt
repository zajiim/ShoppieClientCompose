package com.example.shoppieclient.data.remote.dto.cart


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CartDto(
    @SerialName("cartDetails")
    val cartDetails: List<CartDetailDto>,
    @SerialName("currentPage")
    val currentPage: Int,
    @SerialName("status")
    val status: String,
    @SerialName("totalCartItems")
    val totalCartItems: Int,
    @SerialName("totalPages")
    val totalPages: Int
)