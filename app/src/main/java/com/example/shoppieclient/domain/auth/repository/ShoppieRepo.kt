package com.example.shoppieclient.domain.auth.repository

import com.example.shoppieclient.domain.auth.models.signin.SignInResponse
import com.example.shoppieclient.domain.auth.models.signin.TokenValidationResponse
import com.example.shoppieclient.domain.auth.models.signup.SignUpResponse
import com.example.shoppieclient.utils.Resource
import kotlinx.coroutines.flow.Flow

interface ShoppieRepo {
    fun signUp(
        name: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Flow<Resource<SignUpResponse>>

    fun signIn(
        email: String,
        password: String
    ): Flow<Resource<SignInResponse>>

    fun isTokenValid(
        token: String
    ): Flow<Resource<TokenValidationResponse>>



}