package com.example.shoppieclient.data.remote.api

import com.example.shoppieclient.data.remote.dto.CartCountDto
import com.example.shoppieclient.data.remote.dto.UserDto
import com.example.shoppieclient.data.remote.dto.cart.CartDto
import com.example.shoppieclient.domain.auth.models.cart.AddToCartRequest
import com.example.shoppieclient.domain.auth.models.home.ApiResponse
import com.example.shoppieclient.domain.auth.models.home.ProductDetailsResponse
import com.example.shoppieclient.domain.auth.models.signin.SignInRequest
import com.example.shoppieclient.domain.auth.models.signin.TokenValidationResponse
import com.example.shoppieclient.domain.auth.models.signup.SignUpRequest
import com.example.shoppieclient.domain.auth.models.signup.SignUpResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ShoppieApi {

    @POST("api/signup")
    suspend fun signUp(
        @Body signUpRequest: SignUpRequest
    ): SignUpResponse


    @POST("api/signin")
    suspend fun signIn(
        @Body signInRequest: SignInRequest
    ): UserDto

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

    @GET("api/getTrendingShoes")
    suspend fun getTrendingShoes(
        @Header("x-auth-token") token: String
    ): ApiResponse

    @GET("api/getTopRated")
    suspend fun getTopRated(
        @Header("x-auth-token") token: String
    ): ApiResponse

    @GET("api/suggestedForYou")
    suspend fun suggestedForYou(
        @Header("x-auth-token") token: String
    ): ApiResponse

    @GET("api/get-product/{id}")
    suspend fun getProductDetail(
        @Header("x-auth-token") token: String,
        @Path("id") id: String
    ): ProductDetailsResponse

    @POST("api/add-to-cart")
    suspend fun addToCart(
        @Header("x-auth-token") token: String,
        @Body requestBody: AddToCartRequest
    ): UserDto

    @GET("api/get-cart-count")
    suspend fun getCartCount(
        @Header("x-auth-token") token: String
    ): CartCountDto

    @GET("api/get-all-cart-items")
    suspend fun getAllCartItems(
        @Header("x-auth-token") token: String,
        @Query("page") page: Int,
        @Query("limit") limit: Int,
    ): CartDto

}