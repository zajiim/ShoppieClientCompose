package com.example.shoppieclient.presentation.auth.signup.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
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
import com.example.shoppieclient.presentation.auth.components.CustomTextField
import com.example.shoppieclient.ui.theme.PrimaryBlue

@Composable
fun SignUpContainer(
    modifier: Modifier = Modifier,
    nameValue: () -> String,
    emailValue: () -> String,
    passwordValue: () -> String,
    confirmPasswordValue: () -> String,
    onNameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onConfirmPasswordChanged: (String) -> Unit,
    buttonEnabled: () -> Boolean,
    onSignUpClick: () -> Unit,
    isPasswordVisible: () -> Boolean,
    isConfirmPasswordVisible: () -> Boolean,
    onTrailingIconClickPassword: () -> Unit,
    onTrailingIconClickConfirmPassword: () -> Unit,
    onNameErrorHint: () -> String?,
    onEmailErrorHint: () -> String?,
    onPasswordErrorHint: () -> String?,
    onConfirmPasswordErrorHint: () -> String?,
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
            title = "Name",
            textValue = nameValue(),
            hint = "Enter your name",
            textColor = Color.Black,
            cursorColor = PrimaryBlue,
            onValueChange = onNameChanged,
            onTrailingIconClicked = null,
            errorString = onNameErrorHint()
            )

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
            onTrailingIconClicked = { onTrailingIconClickPassword() },
            visualTransformation = if (isPasswordVisible()) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardType = KeyboardType.Password,
            trailingIcon = if (isPasswordVisible()) visiblePasswordIcon else inVisiblePasswordIcon,
            errorString = onPasswordErrorHint() ?: ""
        )

        CustomTextField(
            modifier = Modifier.fillMaxWidth(),
            title = "Confirm Password",
            textValue = confirmPasswordValue(),
            hint = "Confirm your password",
            textColor = Color.Black,
            cursorColor = PrimaryBlue,
            onValueChange = onConfirmPasswordChanged,
            onTrailingIconClicked = { onTrailingIconClickConfirmPassword() },
            visualTransformation = if (isPasswordVisible()) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardType = KeyboardType.Password,
            trailingIcon = if (isConfirmPasswordVisible()) visiblePasswordIcon else inVisiblePasswordIcon,
            errorString = onConfirmPasswordErrorHint() ?: ""
        )

        CustomButton(
            text = "Sign Up",
            backgroundColor = buttonBackgroundColor,
            contentColor = buttonTextColor,
            onButtonClicked = onSignUpClick,
            isLoading = isLoading(),
            enabled = buttonEnabled(),
            modifier = Modifier.fillMaxWidth()
        )

    }

}