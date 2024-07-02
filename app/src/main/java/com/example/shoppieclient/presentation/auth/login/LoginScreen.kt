package com.example.shoppieclient.presentation.auth.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.shoppieclient.presentation.auth.components.CustomTitle
import com.example.shoppieclient.presentation.auth.login.components.LoginContainer
import com.example.shoppieclient.ui.theme.PrimaryBlue

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        CustomTitle(
            title = "Hello Again!",
            subTitle = "Welcome back you've been missed"
        )
        LoginContainer(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp),
            emailValue = { viewModel.signInState.value.emailInput },
            passwordValue = { viewModel.signInState.value.passwordInput },
            onEmailChanged = viewModel::onEmailChange,
            onPasswordChanged = viewModel::onPasswordChange,
            buttonEnabled = { viewModel.signInState.value.isInputValid },
            onLoginClick = viewModel::onLoginClick,
            isPasswordVisible = { viewModel.signInState.value.isPasswordShown },
            onTrailingIconClick = viewModel::onTogglePasswordVisibility ,
            onEmailErrorHint = { viewModel.signInState.value.emailErrorMsgInput },
            onPasswordErrorHint = { viewModel.signInState.value.passwordErrorMsgInput },
            isLoading = { viewModel.signInState.value.isLoading },
            buttonBackgroundColor = PrimaryBlue,
            buttonTextColor = Color.White
        )



    }
}