package com.example.shoppieclient.domain.main.use_cases

import com.example.shoppieclient.domain.main.datamanager.LocalUserManager

class SaveCartCountUseCase(
    private val localUserManager: LocalUserManager
) {
    suspend operator fun invoke(cartCount: Int) {
        localUserManager.saveCartCount(cartCount)
    }
}