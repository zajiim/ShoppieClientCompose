package com.example.shoppieclient.domain.main.use_cases

import com.example.shoppieclient.domain.main.datamanager.LocalUserManager

class SaveOnBoardingUseCase(
    private val localUserManager: LocalUserManager
) {
    suspend operator fun invoke(onBoardingValue: Boolean) {
        localUserManager.saveOnBoardingValue(onBoardingValue)
    }
}