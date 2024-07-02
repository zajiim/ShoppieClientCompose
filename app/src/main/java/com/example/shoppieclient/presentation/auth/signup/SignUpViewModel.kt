package com.example.shoppieclient.presentation.auth.signup

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppieclient.data.remote.api.ShoppieApi
import com.example.shoppieclient.domain.auth.models.signin.ConfirmPasswordValidationType
import com.example.shoppieclient.domain.auth.models.signin.EmailValidationType
import com.example.shoppieclient.domain.auth.models.signin.NameValidationType
import com.example.shoppieclient.domain.auth.models.signin.PasswordValidationType
import com.example.shoppieclient.domain.auth.models.signup.SignUpRequest
import com.example.shoppieclient.domain.auth.use_cases.signIn.SignInValidationUseCases
import com.example.shoppieclient.domain.auth.use_cases.signUp.SignUpValidationUseCases
import com.example.shoppieclient.presentation.auth.signin.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val shoppieRepo: ShoppieApi,
    private val signUpValidationUseCases: SignUpValidationUseCases
) : ViewModel() {

    var signUpState by mutableStateOf(SignUpState())
        private set


    fun onNameChange(newValue: String) {
        signUpState = signUpState.copy(
            nameInput = newValue
        )
        checkNameInputValidation()
    }

    private fun checkNameInputValidation() {
        val nameValidationResult =
            signUpValidationUseCases.signUpNameValidationUseCase(name = signUpState.nameInput)

        processNameInputValidationType(nameValidationResult)
    }

    private fun processNameInputValidationType(nameValidationResult: NameValidationType) {
        signUpState = when (nameValidationResult) {
            NameValidationType.EMPTY_NAME -> {
                signUpState.copy(
                    nameErrorMsgInput = "Please enter a name", isInputValid = false
                )
            }

            NameValidationType.INVALID_NAME -> {
                signUpState.copy(
                    nameErrorMsgInput = "Please enter a valid name", isInputValid = false
                )
            }

            NameValidationType.VALID_NAME -> {
                signUpState.copy(
                    nameErrorMsgInput = null, isInputValid = true
                )
            }
        }
    }


    fun onEmailChange(newValue: String) {
        signUpState = signUpState.copy(
            emailInput = newValue
        )
        checkEmailInputValidation()
    }

    private fun checkEmailInputValidation() {
        val emailValidationResult =
            signUpValidationUseCases.signUpValidationEmailUseCase(email = signUpState.emailInput)

        processEmailInputValidationType(emailValidationResult)
    }

    private fun processEmailInputValidationType(emailValidationResult: EmailValidationType) {
        signUpState = when (emailValidationResult) {
            EmailValidationType.EMPTY_EMAIL -> {
                signUpState.copy(
                    emailErrorMsgInput = "Please enter an email", isInputValid = false
                )
            }

            EmailValidationType.INVALID_EMAIL -> {
                signUpState.copy(
                    emailErrorMsgInput = "Please enter a valid email", isInputValid = false
                )
            }

            EmailValidationType.VALID_EMAIL -> {
                signUpState.copy(
                    emailErrorMsgInput = null, isInputValid = true
                )
            }
        }
    }

    fun onPasswordChange(newValue: String) {
        signUpState = signUpState.copy(
            passwordInput = newValue
        )
        checkPasswordInputValidation()
    }

    private fun checkPasswordInputValidation() {
        val passwordValidationResult =
            signUpValidationUseCases.signUpValidationPasswordUseCase(password = signUpState.passwordInput)

        processPasswordInputValidationType(passwordValidationResult)
    }

    private fun processPasswordInputValidationType(passwordValidationResult: PasswordValidationType) {
        signUpState = when (passwordValidationResult) {
            PasswordValidationType.EMPTY_PASSWORD -> {
                signUpState.copy(
                    passwordErrorMsgInput = "Please enter a password", isInputValid = false
                )
            }

            PasswordValidationType.INVALID_PASSWORD -> {
                signUpState.copy(
                    passwordErrorMsgInput = "Please enter a valid password", isInputValid = false
                )
            }

            PasswordValidationType.VALID_PASSWORD -> {
                signUpState.copy(
                    passwordErrorMsgInput = null, isInputValid = true
                )
            }
        }
    }

    fun onConfirmPasswordChange(newValue: String) {
        signUpState = signUpState.copy(
            confirmPasswordInput = newValue
        )
        checkConfirmPasswordInputValidation()
    }

    private fun checkConfirmPasswordInputValidation() {
        val confirmPasswordValidationResult = signUpValidationUseCases.signUpConfirmPasswordUseCase(
            password = signUpState.passwordInput, confirmPassword = signUpState.confirmPasswordInput
        )

        processConfirmPasswordInputValidationType(confirmPasswordValidationResult)
    }

    private fun processConfirmPasswordInputValidationType(confirmPasswordValidationResult: ConfirmPasswordValidationType) {
        signUpState = when (confirmPasswordValidationResult) {
            ConfirmPasswordValidationType.EMPTY_PASSWORD -> {
                signUpState.copy(
                    confirmPasswordErrorMsgInput = "Please confirm your password",
                    isInputValid = false
                )
            }

            ConfirmPasswordValidationType.INVALID_PASSWORD -> {
                signUpState.copy(
                    confirmPasswordErrorMsgInput = "Passwords must be equal", isInputValid = false
                )
            }

            ConfirmPasswordValidationType.VALID_PASSWORD -> {
                signUpState.copy(
                    confirmPasswordErrorMsgInput = null, isInputValid = true
                )
            }
        }
    }

    fun onTogglePasswordVisibility() {
        signUpState = signUpState.copy(
            isPasswordShown = !signUpState.isPasswordShown
        )
    }

    fun onToggleConfirmPasswordVisibility() {
        signUpState = signUpState.copy(
            isConfirmPasswordShown = !signUpState.isConfirmPasswordShown
        )
    }

    fun onSignUpClick() {
        signUpState = signUpState.copy(
            isLoading = true
        )

        viewModelScope.launch {
            signUpState = try {
                val signUpResult = shoppieRepo.signUp(
                    SignUpRequest(
                        name = signUpState.nameInput,
                        email = signUpState.emailInput,
                        password = signUpState.passwordInput,
                        confirmPassword = signUpState.confirmPasswordInput
                    )
                )

                signUpState.copy(
                    isSuccessfullyLoggedIn = true, isLoading = false, navigateToLogin = true
                )
            } catch (e: HttpException) {
                if (e.code() == 400) {
                    signUpState.copy(
                        errorMsgLoginProcess = e.message(), isLoading = false
                    )
                } else {
                    Log.e(TAG, "onLoginClick: errorr >>>>>>> ${e.message()}")
                    signUpState.copy(
                        errorMsgLoginProcess = e.message.toString()
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
                signUpState.copy(
                    errorMsgLoginProcess = e.message
                )
            } finally {
                signUpState = signUpState.copy(
                    isLoading = false
                )
            }
        }

    }
}