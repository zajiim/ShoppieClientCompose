package com.example.shoppieclient.domain.auth.use_cases.signIn

import com.example.shoppieclient.domain.auth.models.signin.SignInPasswordValidationType

class ValidationPasswordUseCase {
    operator fun invoke(password: String): SignInPasswordValidationType {

        if (password.isEmpty()) {
            return SignInPasswordValidationType.EMPTY_PASSWORD
        }
        if (password.length < 8) {
            return SignInPasswordValidationType.INVALID_PASSWORD
        }
        return SignInPasswordValidationType.VALID_PASSWORD
    }
}