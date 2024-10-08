package com.example.shoppieclient.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.shoppieclient.data.mapper.signin.toCartCount
import com.example.shoppieclient.data.mapper.signin.toUser
import com.example.shoppieclient.data.mapper.toShoppieItem
import com.example.shoppieclient.data.paging.CartsPagingSource
import com.example.shoppieclient.data.remote.api.ShoppieApi
import com.example.shoppieclient.domain.auth.models.cart.AddToCartRequest
import com.example.shoppieclient.domain.auth.models.cart.Products
import com.example.shoppieclient.domain.auth.models.home.CartCount
import com.example.shoppieclient.domain.auth.models.signin.SignInRequest
import com.example.shoppieclient.domain.auth.models.signin.TokenValidationResponse
import com.example.shoppieclient.domain.auth.models.signin.User
import com.example.shoppieclient.domain.auth.models.signup.SignUpRequest
import com.example.shoppieclient.domain.auth.models.signup.SignUpResponse
import com.example.shoppieclient.domain.auth.repository.ShoppieRepo
import com.example.shoppieclient.domain.models.ShoppieItem
import com.example.shoppieclient.utils.Constants
import com.example.shoppieclient.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ShoppieRepoImpl @Inject constructor(
    private val shoppieApi: ShoppieApi
): ShoppieRepo {
    override fun signUp(
        name: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Flow<Resource<SignUpResponse>> = flow {
        try {
            val response = shoppieApi.signUp(
                SignUpRequest(
                    name, email, password, confirmPassword
                )
            )
            emit(Resource.Success(data = response))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }

    override fun signIn(email: String, password: String): Flow<Resource<User>> = flow {
        try {
            val response = shoppieApi.signIn(
                SignInRequest(
                    email, password
                )
            )
            emit(Resource.Success(data = response.toUser()))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }

    override fun isTokenValid(token: String): Flow<Resource<TokenValidationResponse>> = flow {
        try {
            val response = shoppieApi.isTokenValid(token)
            if (response.status) {
                emit(Resource.Success(data = response))
            } else {
                emit(Resource.Error(message = response.message))
            }
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }

    override fun getPopularBrands(token: String): Flow<Resource<List<ShoppieItem>>> = flow {
        emit(Resource.Loading(true))
        try {
            val response = shoppieApi.getPopularBrands(token = token)
            val shoppieItems = response.products.map { it.toShoppieItem() }
            emit(Resource.Success(shoppieItems))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }

    override fun getPopularBrand(token: String, category: String): Flow<Resource<List<ShoppieItem>>> = flow{
        emit(Resource.Loading(true))
        try {
            val response = shoppieApi.getPopularBrand(token = token, category = category)
            val shoppieItems = response.products.map {
                Log.e("api_mapping", "${it.toShoppieItem().id}: " )
                it.toShoppieItem() }
            emit(Resource.Success(shoppieItems))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }

    override fun getNewArrivals(token: String): Flow<Resource<List<ShoppieItem>>> = flow {
        emit(Resource.Loading(true))
        try {
            val response = shoppieApi.getNewArrivals(token = token)
            val shoppieItems = response.products.map { it.toShoppieItem() }
            emit(Resource.Success(shoppieItems))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }

    override fun getTrendingShoes(token: String): Flow<Resource<List<ShoppieItem>>> = flow {
        emit(Resource.Loading(true))
        try {
            val response = shoppieApi.getTrendingShoes(token = token)
            val shoppieItems = response.products.map { it.toShoppieItem() }
            emit(Resource.Success(shoppieItems))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }

    override fun getTopRated(token: String): Flow<Resource<List<ShoppieItem>>> = flow {
        emit(Resource.Loading(true))
        try {
            val response = shoppieApi.getTopRated(token = token)
            val shoppieItems = response.products.map { it.toShoppieItem() }
            emit(Resource.Success(shoppieItems))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }

    override fun suggestedForYou(token: String): Flow<Resource<List<ShoppieItem>>> = flow {
        emit(Resource.Loading(true))
        try {
            val response = shoppieApi.suggestedForYou(token = token)
            val shoppieItems = response.products.map { it.toShoppieItem() }
            emit(Resource.Success(shoppieItems))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }

    override fun getProductDetail(token: String, id: String): Flow<Resource<ShoppieItem>> = flow {
        emit(Resource.Loading(true))
        try {
            val response = shoppieApi.getProductDetail(token, id)
            val shoppieItem = response.product.toShoppieItem()
            emit(Resource.Success(shoppieItem))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }

    override fun addToCart(token: String, id: String): Flow<Resource<User>> = flow {
        emit(Resource.Loading(true))
        try {
            val response = shoppieApi.addToCart(token, AddToCartRequest(id = id))
            Log.e("tag_cart_count_api_impl", "before >>>>${response} ")
            val userItem = response.toUser()
            Log.e("tag_cart_count_api_impl", "after >>>>>> ${userItem} ")
            emit(Resource.Success(userItem))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }

    override fun getCartCount(token: String): Flow<Resource<CartCount>> = flow {
        emit(Resource.Loading(true))
        try {
            val response = shoppieApi.getCartCount(token)
            emit(Resource.Success(response.toCartCount()))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString()))

        }
    }

    override fun getCartItems(token: String, page: Int, limit: Int): Flow<PagingData<Products>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.PER_PAGE_ITEMS
            ),
            pagingSourceFactory = {
                CartsPagingSource(
                    shoppieApi = shoppieApi,
                    token = token
                )
            }
        ).flow

    }


}