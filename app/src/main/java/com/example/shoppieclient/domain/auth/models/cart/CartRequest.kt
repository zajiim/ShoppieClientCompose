package com.example.shoppieclient.domain.auth.models.cart

import com.google.gson.annotations.SerializedName

data class AddToCartRequest(
    @SerializedName("id")
    val id: String
)