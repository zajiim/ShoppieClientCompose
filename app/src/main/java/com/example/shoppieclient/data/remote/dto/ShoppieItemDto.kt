package com.example.shoppieclient.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ShoppieItemDto(
    @SerialName("category")
    val category: String,
    @SerialName("description")
    val description: String,
    @SerialName("_id")
    val id: String,
    @SerialName("images")
    val images: List<String>,
    @SerialName("name")
    val name: String,
    @SerialName("price")
    val price: Int,
    @SerialName("quantity")
    val quantity: Int,
    @SerialName("__v")
    val v: Int
)