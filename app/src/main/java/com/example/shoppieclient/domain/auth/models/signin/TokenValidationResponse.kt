package com.example.shoppieclient.domain.auth.models.signin

import com.google.gson.annotations.SerializedName

data class TokenValidationResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
)
