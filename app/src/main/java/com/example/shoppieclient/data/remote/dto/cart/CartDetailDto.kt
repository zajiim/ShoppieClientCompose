package com.example.shoppieclient.data.remote.dto.cart


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CartDetailDto(
    @SerialName("_id")
    val _id: String,
    @SerialName("product")
    val product: ProductDto,
    @SerialName("quantity")
    val quantity: Int
)