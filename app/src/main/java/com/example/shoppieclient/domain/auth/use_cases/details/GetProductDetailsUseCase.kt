package com.example.shoppieclient.domain.auth.use_cases.details

import com.example.shoppieclient.domain.auth.repository.ShoppieRepo
import com.example.shoppieclient.domain.models.ShoppieItem
import com.example.shoppieclient.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductDetailsUseCase @Inject constructor(
    private val shoppieRepo: ShoppieRepo
) {
    operator fun invoke(token: String, id: String): Flow<Resource<ShoppieItem>> {
        return shoppieRepo.getProductDetail(token, id)
    }
}