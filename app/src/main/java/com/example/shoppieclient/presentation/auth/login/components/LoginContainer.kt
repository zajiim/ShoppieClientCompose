package com.example.shoppieclient.presentation.auth.login.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.shoppieclient.R
import com.example.shoppieclient.presentation.auth.components.CustomButton
import com.example.shoppieclient.presentation.auth.components.CustomSocialMediaButton
import com.example.shoppieclient.presentation.auth.components.CustomTextButtonQuery
import com.example.shoppieclient.presentation.auth.components.CustomTextField
import com.example.shoppieclient.ui.theme.PrimaryBlue
import com.example.shoppieclient.ui.theme.SubTitleColor
import com.example.shoppieclient.ui.theme.TitleColor

@Composable
fun LoginContainer(
    modifier: Modifier = Modifier,
    emailValue: () -> String,
    passwordValue: () -> String,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    buttonEnabled: () -> Boolean,
    onLoginClick: () -> Unit,
    isPasswordVisible: () -> Boolean,
    onTrailingIconClick: () -> Unit,
    onEmailErrorHint: () -> String?,
    onPasswordErrorHint: () -> String?,
    isLoading: () -> Boolean,
    buttonBackgroundColor: Color,
    buttonTextColor: Color,
) {

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
        val visiblePasswordIcon = ImageVector.vectorResource(id = R.drawable.ic_visibility_on)
        val inVisiblePasswordIcon = ImageVector.vectorResource(id = R.drawable.ic_visibility_off)

        CustomTextField(
            modifier = Modifier.fillMaxWidth(),
            title = "Email Address",
            textValue = emailValue(),
            hint = "Enter your email",
            textColor = Color.Black,
            cursorColor = PrimaryBlue,
            onValueChange = onEmailChanged,
            onTrailingIconClicked = null,
            trailingIcon = Icons.Default.Email,
            errorString = onEmailErrorHint()
            )

        CustomTextField(
            modifier = Modifier.fillMaxWidth(),
            title = "Password",
            textValue = passwordValue(),
            hint = "Enter your password",
            textColor = Color.Black,
            cursorColor = PrimaryBlue,
            onValueChange = onPasswordChanged,
            onTrailingIconClicked = { onTrailingIconClick() },
            visualTransformation = if (isPasswordVisible()) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardType = KeyboardType.Password,
            trailingIcon = if (isPasswordVisible()) visiblePasswordIcon else inVisiblePasswordIcon,
            errorString = onPasswordErrorHint() ?: ""
        )

        CustomButton(
            text = "Sign In",
            backgroundColor = buttonBackgroundColor,
            contentColor = buttonTextColor,
            onButtonClicked = onLoginClick,
            isLoading = isLoading(),
            enabled = buttonEnabled(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(26.dp))


        CustomSocialMediaButton(
            title = "Sign in with google",
            icon = R.drawable.ic_gmail_logo,
            modifier = Modifier,
            onClick = { }
        )




    }

    Column(modifier = modifier.fillMaxHeight(0.2f)) {
        CustomTextButtonQuery(
            title = "Don\'t have an account?",
            clickableText = "Sign Up For Free"
        ) {

        }
    }

}