package com.example.shoppieclient.domain.auth.use_cases.signUp

import com.example.shoppieclient.domain.auth.models.signin.ConfirmPasswordValidationType

class SignUpConfirmPasswordUseCase {
    operator fun invoke(password: String, confirmPassword: String): ConfirmPasswordValidationType {
        if (confirmPassword.isEmpty()) {
            return ConfirmPasswordValidationType.EMPTY_PASSWORD
        }
        if (confirmPassword != password) {
            return ConfirmPasswordValidationType.INVALID_PASSWORD
        }
        return ConfirmPasswordValidationType.VALID_PASSWORD
    }
}