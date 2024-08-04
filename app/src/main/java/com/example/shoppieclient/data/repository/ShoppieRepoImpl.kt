package com.example.shoppieclient.data.repository

import com.example.shoppieclient.data.mapper.toShoppieItem
import com.example.shoppieclient.data.remote.api.ShoppieApi
import com.example.shoppieclient.domain.auth.models.signin.SignInRequest
import com.example.shoppieclient.domain.auth.models.signin.SignInResponse
import com.example.shoppieclient.domain.auth.models.signin.TokenValidationResponse
import com.example.shoppieclient.domain.auth.models.signup.SignUpRequest
import com.example.shoppieclient.domain.auth.models.signup.SignUpResponse
import com.example.shoppieclient.domain.auth.repository.ShoppieRepo
import com.example.shoppieclient.domain.models.ShoppieItem
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

    override fun signIn(email: String, password: String): Flow<Resource<SignInResponse>> = flow {
        try {
            val response = shoppieApi.signIn(
                SignInRequest(
                    email, password
                )
            )
            emit(Resource.Success(data = response))
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
            val shoppieItems = response.products.map { it.toShoppieItem() }
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


}