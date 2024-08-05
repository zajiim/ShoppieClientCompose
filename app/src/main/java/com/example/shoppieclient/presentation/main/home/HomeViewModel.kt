package com.example.shoppieclient.presentation.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppieclient.domain.auth.use_cases.home.GetNewArrivalsUseCase
import com.example.shoppieclient.domain.auth.use_cases.home.GetPopularBrandsIndividualUseCase
import com.example.shoppieclient.domain.auth.use_cases.home.HomeApiUseCases
import com.example.shoppieclient.domain.main.use_cases.DataStoreUseCases
import com.example.shoppieclient.domain.models.ShoppieItem
import com.example.shoppieclient.utils.Resource
import com.example.shoppieclient.utils.searchKeys
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dataStoreUseCase: DataStoreUseCases,
    private val homeApiUseCases: HomeApiUseCases
): ViewModel() {

    private val _newArrivals = MutableStateFlow<Resource<List<ShoppieItem>>>(Resource.Loading(true))
    val newArrivals = _newArrivals.asStateFlow()

    private val _popularItems = MutableStateFlow<Resource<List<ShoppieItem>>>(Resource.Loading(true))
    val popularItems = _popularItems.asStateFlow()

    private val _trendingShoes = MutableStateFlow<Resource<List<ShoppieItem>>>(Resource.Loading(true))
    val trendingShoes = _trendingShoes.asStateFlow()

    private val _topRated = MutableStateFlow<Resource<List<ShoppieItem>>>(Resource.Loading(true))
    val topRated = _topRated.asStateFlow()

    private val _suggestedForYou = MutableStateFlow<Resource<List<ShoppieItem>>>(Resource.Loading(true))
    val suggestedForYou = _suggestedForYou.asStateFlow()

    private val selectedCategory = MutableStateFlow(searchKeys.keys.first())


    init {
        fetchNewArrivals()
        fetchPopularItems(searchKeys.keys.first())
        fetchTrendingShoes()
        fetchTopRatedShoes()
        fetchSuggestedForYou()
    }

    private fun fetchPopularItems(category: String) = viewModelScope.launch {
        val token = dataStoreUseCase.readTokenUseCase().firstOrNull()
        if (token != null) {
            getPopularItems(token, category)
        } else {
            _popularItems.value = Resource.Error("No valid token found")
        }
    }

    private fun getPopularItems(token: String, category: String) = viewModelScope.launch {
        homeApiUseCases.popularBrandsIndividualUseCase(token, category).collect { result ->
            _popularItems.value = result
        }
    }


    private fun fetchNewArrivals() {
        viewModelScope.launch {
            val token = dataStoreUseCase.readTokenUseCase().firstOrNull()
            if (token != null) {
                getNewArrivals(token)
            } else {
                _newArrivals.value = Resource.Error("No valid token available")
            }
        }
    }

    private fun getNewArrivals(token: String) = viewModelScope.launch {
        homeApiUseCases.newArrivalsUseCase(token).collect { result ->
            _newArrivals.value = result
        }
    }

    private fun fetchTrendingShoes() {
        viewModelScope.launch {
            val token = dataStoreUseCase.readTokenUseCase().firstOrNull()
            if (token != null) {
                getTrendingShoes(token)
            } else {
                _newArrivals.value = Resource.Error("No valid token available")
            }
        }
    }

    private fun getTrendingShoes(token: String) = viewModelScope.launch {
        homeApiUseCases.trendingShoesUseCase(token).collect { result ->
            _newArrivals.value = result
        }
    }

    private fun fetchTopRatedShoes() {
        viewModelScope.launch {
            val token = dataStoreUseCase.readTokenUseCase().firstOrNull()
            if (token != null) {
                getTopRated(token)
            } else {
                _newArrivals.value = Resource.Error("No valid token available")
            }
        }
    }

    private fun getTopRated(token: String) = viewModelScope.launch {
        homeApiUseCases.topRatedUseCase(token).collect { result ->
            _newArrivals.value = result
        }
    }

    private fun fetchSuggestedForYou() {
        viewModelScope.launch {
            val token = dataStoreUseCase.readTokenUseCase().firstOrNull()
            if (token != null) {
                getSuggestedForYou(token)
            } else {
                _newArrivals.value = Resource.Error("No valid token available")
            }
        }
    }

    private fun getSuggestedForYou(token: String) = viewModelScope.launch {
        homeApiUseCases.suggestedUseCase(token).collect { result ->
            _newArrivals.value = result
        }
    }








    fun searchItems(query: String) {
        viewModelScope.launch {

        }
    }

    fun onChipSelected(category: String) {
        selectedCategory.value = category
        fetchPopularItems(category)
    }


}