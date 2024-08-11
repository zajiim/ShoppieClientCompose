package com.example.shoppieclient.domain.main.datamanager

import kotlinx.coroutines.flow.Flow

interface LocalUserManager {
    suspend fun saveOnBoardingValue(value: Boolean): Boolean

    fun readOnBoardingValue(): Flow<Boolean>

    suspend fun saveAppToken(token: String)

    fun readAppToken(): Flow<String?>

}