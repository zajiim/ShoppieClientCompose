package com.example.shoppieclient.domain.auth.use_cases.cart

import androidx.paging.PagingData
import com.example.shoppieclient.domain.auth.models.cart.Products
import com.example.shoppieclient.domain.auth.repository.ShoppieRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCartUseCase @Inject constructor(
    private val shoppieRepo: ShoppieRepo
) {
    operator fun invoke(token: String, page: Int, limit: Int): Flow<PagingData<Products>> {
        return shoppieRepo.getCartItems(token, page, limit)
    }
}