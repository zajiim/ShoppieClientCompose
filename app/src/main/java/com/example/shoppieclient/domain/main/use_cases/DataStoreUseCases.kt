package com.example.shoppieclient.domain.main.use_cases

data class DataStoreUseCases(
    val saveTokenUseCase: SaveTokenUseCase,
    val readTokenUseCase: ReadTokenUseCase,
    val saveOnBoardingUseCase: SaveOnBoardingUseCase,
    val readOnBoardingUseCase: ReadOnBoardingUseCase
)
