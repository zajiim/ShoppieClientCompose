package com.example.shoppieclient.domain.auth.use_cases.home

import com.example.shoppieclient.domain.auth.repository.ShoppieRepo
import com.example.shoppieclient.domain.models.ShoppieItem
import com.example.shoppieclient.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularBrandsIndividualUseCase @Inject constructor(
    private val shoppieRepo: ShoppieRepo
) {
    operator fun invoke(token: String, category: String): Flow<Resource<List<ShoppieItem>>> {
         return shoppieRepo.getPopularBrand(token, category)
    }
}