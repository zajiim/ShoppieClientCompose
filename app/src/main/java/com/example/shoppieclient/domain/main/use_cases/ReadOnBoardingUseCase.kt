package com.example.shoppieclient.domain.main.use_cases

import com.example.shoppieclient.domain.main.datamanager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadOnBoardingUseCase(
    private val localUserManager: LocalUserManager
) {
    suspend operator fun invoke(): Flow<Boolean> {
        return localUserManager.readOnBoardingValue()
    }
}