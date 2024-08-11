package com.example.shoppieclient.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CartCountDto(
    @SerialName("cartCount")
    val cartCount: Int,
    @SerialName("status")
    val status: String
)