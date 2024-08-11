package com.example.shoppieclient.data.datamanager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.shoppieclient.domain.main.datamanager.LocalUserManager
import com.example.shoppieclient.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalUserManagerImpl(
    private val context: Context
): LocalUserManager {
    override suspend fun saveOnBoardingValue(value: Boolean): Boolean {
        context.datastore.edit { prefs ->
            prefs[PreferencesKeys.ONBOARDING_VALUE] = value
        }
        return true
    }

    override fun readOnBoardingValue(): Flow<Boolean> {
        return context.datastore.data.map { prefs ->
            prefs[PreferencesKeys.ONBOARDING_VALUE] ?: false
        }
    }

    override suspend fun saveAppToken(token: String) {
        context.datastore.edit { prefs ->
            prefs[PreferencesKeys.DATASTORE_TOKEN] = token
        }
    }

    override fun readAppToken(): Flow<String?> {
        return context.datastore.data.map { prefs ->
            prefs[PreferencesKeys.DATASTORE_TOKEN]
        }
    }

}

private val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = Constants.DATASTORE_NAME)


private object PreferencesKeys {
    val DATASTORE_TOKEN = stringPreferencesKey(name = Constants.DATASTORE_TOKEN)
    val ONBOARDING_VALUE = booleanPreferencesKey(name = Constants.ONBOARDING_VALUE)
}