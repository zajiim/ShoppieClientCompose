package com.example.shoppieclient.domain.auth.use_cases.signIn

data class SignInValidationUseCases(
    val signInValidationEmailUseCase: SignInValidationEmailUseCase,
    val signInValidationPasswordUseCase: SignInValidationPasswordUseCase
)
