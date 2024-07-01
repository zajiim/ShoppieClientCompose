package com.example.shoppieclient.domain.auth.models.signup


import com.google.gson.annotations.SerializedName

data class SignUpResponse(
    @SerializedName("address")
    val address: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("__v")
    val v: Int
)