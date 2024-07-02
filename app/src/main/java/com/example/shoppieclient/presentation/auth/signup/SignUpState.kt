package com.example.shoppieclient.presentation.auth.signup

data class SignUpState(
    val nameInput: String = "",
    val emailInput: String = "",
    val passwordInput: String = "",
    val confirmPasswordInput: String = "",
    val isInputValid: Boolean = false,
    val isPasswordShown: Boolean = false,
    val isConfirmPasswordShown: Boolean = false,
    val nameErrorMsgInput: String? = null,
    val emailErrorMsgInput: String? = null,
    val passwordErrorMsgInput: String? = null,
    val confirmPasswordErrorMsgInput: String? = null,
    val isLoading: Boolean = false,
    val isSuccessfullyLoggedIn: Boolean = false,
    val navigateToLogin: Boolean = false,
    val afterSuccessfullyLoggedIn: String? = null,
    val errorMsgLoginProcess: String? = null
)
