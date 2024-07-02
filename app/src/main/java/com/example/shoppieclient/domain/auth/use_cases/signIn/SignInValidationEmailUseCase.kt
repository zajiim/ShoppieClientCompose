package com.example.shoppieclient.domain.auth.use_cases.signIn

import android.util.Patterns
import com.example.shoppieclient.domain.auth.models.signin.SignInEmailValidationType

class ValidationEmailUseCase {
    operator fun invoke(email: String): SignInEmailValidationType {
        if (email.isEmpty()) {
            return SignInEmailValidationType.EMPTY_EMAIL
        }
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches().not()) {
            return SignInEmailValidationType.INVALID_EMAIL
        }
        return SignInEmailValidationType.VALID_EMAIL
    }
}