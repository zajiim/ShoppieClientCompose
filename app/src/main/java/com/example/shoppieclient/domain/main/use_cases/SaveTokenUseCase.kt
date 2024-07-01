package com.example.shoppieclient.domain.main.use_cases

import com.example.shoppieclient.domain.main.datamanager.LocalUserManager

class SaveTokenUseCase(
    private val localUserManager: LocalUserManager
) {
    suspend operator fun invoke(token: String) {
        localUserManager.saveAppToken(token)
    }
}