package com.example.shoppieclient.presentation.auth.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.shoppieclient.R
import com.example.shoppieclient.core.navigation.AuthScreen
import com.example.shoppieclient.presentation.auth.components.CustomAlertBox
import com.example.shoppieclient.presentation.auth.components.CustomSocialMediaButton
import com.example.shoppieclient.presentation.auth.components.CustomTextButtonQuery
import com.example.shoppieclient.presentation.auth.components.CustomTitle
import com.example.shoppieclient.presentation.auth.signup.components.SignUpContainer
import com.example.shoppieclient.presentation.on_boarding.components.CustomButtonOnBoarding
import com.example.shoppieclient.ui.theme.PrimaryBlue

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val signUpState = viewModel.signUpState
    var showDialog by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = signUpState.navigateToLogin) {
        if (signUpState.navigateToLogin) {
            showDialog = true
        }
    }

    if (showDialog) {
        CustomAlertBox(
            message = "Your account has been successfully created, Please sign in",
            onConfirm = {
                navController.navigateUp()
            },
            onCancel = {
                showDialog = false
            }
        )
    }
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        item {
            CustomTitle(
                title = "Create Account",
                subTitle = "Let\'s create account together"
            )
        }
        item {
            SignUpContainer(
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp),
                nameValue = { viewModel.signUpState.nameInput },
                emailValue = { viewModel.signUpState.emailInput },
                passwordValue = { viewModel.signUpState.passwordInput },
                confirmPasswordValue = { viewModel.signUpState.confirmPasswordInput },
                onNameChanged = viewModel::onNameChange,
                onEmailChanged = viewModel::onEmailChange,
                onPasswordChanged = viewModel::onPasswordChange,
                onConfirmPasswordChanged = viewModel::onConfirmPasswordChange,
                buttonEnabled = { viewModel.signUpState.isInputValid },
                onSignUpClick = viewModel::onSignUpClick,
                isPasswordVisible = { viewModel.signUpState.isPasswordShown },
                isConfirmPasswordVisible = { viewModel.signUpState.isConfirmPasswordShown },
                onTrailingIconClickPassword = viewModel::onTogglePasswordVisibility,
                onTrailingIconClickConfirmPassword = viewModel::onToggleConfirmPasswordVisibility,
                onNameErrorHint = { viewModel.signUpState.nameErrorMsgInput },
                onEmailErrorHint = { viewModel.signUpState.emailErrorMsgInput },
                onPasswordErrorHint = { viewModel.signUpState.passwordErrorMsgInput },
                onConfirmPasswordErrorHint = { viewModel.signUpState.confirmPasswordErrorMsgInput },
                isLoading = { viewModel.signUpState.isLoading },
                buttonBackgroundColor = PrimaryBlue,
                buttonTextColor = Color.White
            )

            Spacer(modifier = Modifier.height(26.dp))
        }


        item {
            CustomSocialMediaButton(
                title = "Sign in with google",
                icon = R.drawable.ic_gmail_logo,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                onClick = { }
            )

            Spacer(modifier = Modifier.height(26.dp))

            CustomTextButtonQuery(
                title = "Already have an account?",
                clickableText = "Sign In"
            ) {
                navController.navigateUp()
            }
        }

    }
    
}