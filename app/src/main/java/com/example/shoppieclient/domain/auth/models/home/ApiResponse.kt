package com.example.shoppieclient.domain.auth.models.home

import com.example.shoppieclient.data.remote.dto.ShoppieItemDto
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    val status: String,
    val totalProducts: Int,
    val products: List<ShoppieItemDto>
)


@Serializable
data class ProductDetailsResponse(
    val status: String,
    val product: ShoppieItemDto
)