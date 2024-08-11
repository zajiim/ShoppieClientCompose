package com.example.shoppieclient.domain.auth.use_cases.main.navbar

import com.example.shoppieclient.domain.auth.models.home.CartCount
import com.example.shoppieclient.domain.auth.repository.ShoppieRepo
import com.example.shoppieclient.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCartCountUseCase @Inject constructor(
    private val shoppieRepo: ShoppieRepo
) {

    operator fun invoke(token: String): Flow<Resource<CartCount>> {
        return shoppieRepo.getCartCount(token)
    }
}