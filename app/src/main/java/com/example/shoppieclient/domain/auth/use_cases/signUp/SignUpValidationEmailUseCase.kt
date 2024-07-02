package com.example.shoppieclient.domain.auth.use_cases.signUp

import android.util.Patterns
import com.example.shoppieclient.domain.auth.models.signin.EmailValidationType


class SignUpValidationEmailUseCase {
    operator fun invoke(email: String): EmailValidationType {
        if (email.isEmpty()) {
            return EmailValidationType.EMPTY_EMAIL
        }
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches().not()) {
            return EmailValidationType.INVALID_EMAIL
        }
        return EmailValidationType.VALID_EMAIL
    }
}