package com.example.shoppieclient.domain.auth.use_cases.signIn

import com.example.shoppieclient.domain.auth.models.signin.PasswordValidationType

class SignInValidationPasswordUseCase {
    operator fun invoke(password: String): PasswordValidationType {

        if (password.isEmpty()) {
            return PasswordValidationType.EMPTY_PASSWORD
        }
        if (password.length < 8) {
            return PasswordValidationType.INVALID_PASSWORD
        }
        return PasswordValidationType.VALID_PASSWORD
    }
}