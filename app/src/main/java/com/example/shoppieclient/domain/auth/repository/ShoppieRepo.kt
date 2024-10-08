package com.example.shoppieclient.domain.auth.repository

import androidx.paging.PagingData
import com.example.shoppieclient.data.remote.dto.CartCountDto
import com.example.shoppieclient.domain.auth.models.cart.Products
import com.example.shoppieclient.domain.auth.models.home.CartCount
import com.example.shoppieclient.domain.auth.models.signin.TokenValidationResponse
import com.example.shoppieclient.domain.auth.models.signin.User
import com.example.shoppieclient.domain.auth.models.signup.SignUpResponse
import com.example.shoppieclient.domain.models.ShoppieItem
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
    ): Flow<Resource<User>>

    fun isTokenValid(
        token: String
    ): Flow<Resource<TokenValidationResponse>>

    fun getPopularBrands(token: String): Flow<Resource<List<ShoppieItem>>>

    fun getPopularBrand(token: String, category: String): Flow<Resource<List<ShoppieItem>>>

    fun getNewArrivals(token: String): Flow<Resource<List<ShoppieItem>>>

    fun getTrendingShoes(token: String): Flow<Resource<List<ShoppieItem>>>

    fun getTopRated(token: String): Flow<Resource<List<ShoppieItem>>>

    fun suggestedForYou(token: String): Flow<Resource<List<ShoppieItem>>>

    fun getProductDetail(token: String, id: String): Flow<Resource<ShoppieItem>>

    fun addToCart(token: String, id: String): Flow<Resource<User>>

    fun getCartCount(token: String): Flow<Resource<CartCount>>

    fun getCartItems(token: String, page: Int, limit: Int): Flow<PagingData<Products>>

}