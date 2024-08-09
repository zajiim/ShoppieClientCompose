package com.example.shoppieclient.domain.auth.use_cases.details

import com.example.shoppieclient.domain.auth.models.signin.User
import com.example.shoppieclient.domain.auth.repository.ShoppieRepo
import com.example.shoppieclient.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddToCartUseCases @Inject constructor(
    private val shoppieRepo: ShoppieRepo
) {
    operator fun invoke(token: String, id: String): Flow<Resource<User>> {
        return shoppieRepo.addToCart(token, id)
    }
}