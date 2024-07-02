package com.example.shoppieclient.presentation.auth.signin

data class SignInState(
    val emailInput: String = "",
    val passwordInput: String = "",
    val isInputValid: Boolean = false,
    val isPasswordShown: Boolean = false,
    val emailErrorMsgInput: String? = null,
    val passwordErrorMsgInput: String? = null,
    val isLoading: Boolean = false,
    val isSuccessfullyLoggedIn: Boolean = false,
    val navigateToMain: Boolean = false,
    val afterSuccessfullyLoggedIn: String? = null,
    val errorMsgLoginProcess: String? = null
)