package com.example.shoppieclient.domain.auth.use_cases.signIn

data class SignInValidationUseCases(
    val validationEmailUseCase: ValidationEmailUseCase,
    val validationPasswordUseCase: ValidationPasswordUseCase
)
