package com.example.shoppieclient.data.remote.api

import com.example.shoppieclient.domain.auth.models.home.ApiResponse
import com.example.shoppieclient.domain.auth.models.signin.SignInRequest
import com.example.shoppieclient.domain.auth.models.signin.SignInResponse
import com.example.shoppieclient.domain.auth.models.signin.TokenValidationResponse
import com.example.shoppieclient.domain.auth.models.signup.SignUpRequest
import com.example.shoppieclient.domain.auth.models.signup.SignUpResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ShoppieApi {

    @POST("api/signup")
    suspend fun signUp(
        @Body signUpRequest: SignUpRequest
    ): SignUpResponse


    @POST("api/signin")
    suspend fun signIn(
        @Body signInRequest: SignInRequest
    ): SignInResponse

    @POST("api/tokenIsValid")
    suspend fun isTokenValid(
        @Header("x-auth-token") token: String
    ): TokenValidationResponse

    @GET("api/popularBrands")
    suspend fun getPopularBrands(
        @Header("x-auth-token") token: String
    ): ApiResponse

    @GET("api/popularBrand/{brand}")
    suspend fun getPopularBrand(
        @Header("x-auth-token") token: String,
        @Path("brand") category: String,
    ): ApiResponse

    @GET("api/newArrivals")
    suspend fun getNewArrivals(
        @Header("x-auth-token") token: String
    ): ApiResponse


}