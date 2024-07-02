package com.example.shoppieclient.domain.auth.use_cases.signUp

data class SignUpValidationUseCases(
    val signUpNameValidationUseCase: SignUpNameValidationUseCase,
    val signUpValidationEmailUseCase: SignUpValidationEmailUseCase,
    val signUpValidationPasswordUseCase: SignUpValidationPasswordUseCase,
    val signUpConfirmPasswordUseCase: SignUpConfirmPasswordUseCase
)
