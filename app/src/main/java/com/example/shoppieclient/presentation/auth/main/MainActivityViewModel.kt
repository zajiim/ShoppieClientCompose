package com.example.shoppieclient.presentation.auth.main

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppieclient.core.navigation.Graph
import com.example.shoppieclient.data.remote.api.ShoppieApi
import com.example.shoppieclient.domain.main.use_cases.DataStoreUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

const val TAG = "MainActivityViewModel"
@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val dataStoreUseCase: DataStoreUseCases,
    private val shoppieApi: ShoppieApi
): ViewModel(){
    var splashCondition by mutableStateOf(true)
        private set

    var startDestination by mutableStateOf(Graph.AUTH)
        private set



    init {
        viewModelScope.launch {
            dataStoreUseCase.readOnBoardingUseCase().onEach { onBoardingValue ->
                Log.e(TAG, "onboarding value ==> $onBoardingValue")
                if (onBoardingValue) {
                    dataStoreUseCase.readTokenUseCase().onEach { token ->
                        Log.e(TAG, "token value ==> $token")
                        val isTokenValid = token?.let { shoppieApi.isTokenValid(it) }
                        startDestination = if (isTokenValid?.status == true) {
                            Log.e(TAG, "token value true case ==> $token")
                            Graph.MAIN
                        } else {
                            Log.e(TAG, "token value else case ==> $token")
                            Graph.AUTH
                        }
                        delay(300)
                        splashCondition = false
                    }.launchIn(viewModelScope)
                } else {
                    Log.e(TAG, "onboarding value false ==>")
                    startDestination = Graph.ON_BOARDING
                    splashCondition = false
                }
            }.launchIn(viewModelScope)
        }
    }

}