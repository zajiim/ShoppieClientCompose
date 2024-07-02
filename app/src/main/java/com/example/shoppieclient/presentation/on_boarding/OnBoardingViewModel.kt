package com.example.shoppieclient.presentation.on_boarding

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppieclient.domain.main.use_cases.DataStoreUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val useCases: DataStoreUseCases
): ViewModel() {
    fun onEvent(event: OnBoardingEvent) {
        when(event) {
            OnBoardingEvent.saveOnBoardingData -> {
                saveOnboardingData()
            }
        }
    }


    private fun saveOnboardingData() = viewModelScope.launch {
        useCases.saveOnBoardingUseCase(true)
        Log.e("tag_onboarding_viewmodel", "saveOnboardingData: ..... ")
    }

}