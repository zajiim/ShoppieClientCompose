package com.example.shoppieclient.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.shoppieclient.data.mapper.cart.toCart
import com.example.shoppieclient.data.mapper.cart.toCartDetail
import com.example.shoppieclient.data.remote.api.ShoppieApi
import com.example.shoppieclient.domain.auth.models.cart.Products
import com.example.shoppieclient.domain.auth.repository.ShoppieRepo
import com.example.shoppieclient.utils.Constants

class CartsPagingSource(
    private val shoppieApi: ShoppieApi,
    private val token: String
): PagingSource<Int, Products>() {
    override fun getRefreshKey(state: PagingState<Int, Products>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Products> {
        val currentPage = params.key ?: Constants.INITIAL_PAGE_INDEX
        return try {
            val response = shoppieApi.getAllCartItems(
                token = token,
                page = currentPage,
                limit = params.loadSize
            )
            val endOfPaginationReached = response.cartDetails.isEmpty()
            LoadResult.Page(
                data = response.toCart().cartDetails.map { it.product },
                prevKey = if (currentPage == Constants.INITIAL_PAGE_INDEX) null else currentPage - 1,
                nextKey = if (endOfPaginationReached) null else currentPage + 1
            )
        }  catch (e:Exception) {
            LoadResult.Error(e)
        }
    }
}