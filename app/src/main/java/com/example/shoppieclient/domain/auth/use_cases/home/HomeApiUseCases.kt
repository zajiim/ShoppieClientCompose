package com.example.shoppieclient.domain.auth.use_cases.home

data class HomeApiUseCases(
    val popularBrandsUseCase: GetPopularBrandsUseCase,
    val popularBrandsIndividualUseCase: GetPopularBrandsIndividualUseCase,
    val newArrivalsUseCase: GetNewArrivalsUseCase,
    val trendingShoesUseCase: GetTrendingShoesUseCase,
    val topRatedUseCase: GetTopRatedUseCase,
    val suggestedUseCase: GetSuggestedUseCase
)
