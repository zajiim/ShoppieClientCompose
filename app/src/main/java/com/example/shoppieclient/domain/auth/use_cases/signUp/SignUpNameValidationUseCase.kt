package com.example.shoppieclient.domain.auth.use_cases.signUp

import androidx.compose.foundation.isSystemInDarkTheme
import com.example.shoppieclient.domain.auth.models.signin.NameValidationType

class SignUpNameValidationUseCase {
    operator fun invoke(name: String): NameValidationType {
        if (name.isEmpty()) {
            return NameValidationType.EMPTY_NAME
        }
        if (name.length < 5) {
            return NameValidationType.INVALID_NAME
        }
        return NameValidationType.VALID_NAME
    }
}