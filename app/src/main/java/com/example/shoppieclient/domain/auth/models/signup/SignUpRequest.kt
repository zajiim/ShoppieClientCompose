package com.example.shoppieclient.domain.auth.models.signup

data class SignUpRequest(
    val name: String,
    val email: String,
    val password: String,
    val confirmPassword: String
)
