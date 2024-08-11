package com.example.shoppieclient.data.remote.dto.cart


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductDto(
    @SerialName("brand")
    val brand: String,
    @SerialName("category")
    val category: String,
    @SerialName("description")
    val description: String,
    @SerialName("_id")
    val _id: String,
    @SerialName("images")
    val images: List<String>,
    @SerialName("name")
    val name: String,
    @SerialName("price")
    val price: Int,
    @SerialName("productId")
    val productId: String,
    @SerialName("quantity")
    val quantity: Int,
    @SerialName("__v")
    val v: Int
)