package com.example.shoppieclient.presentation.auth.login

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppieclient.data.remote.api.ShoppieApi
import com.example.shoppieclient.domain.auth.models.signin.SignInEmailValidationType
import com.example.shoppieclient.domain.auth.models.signin.SignInPasswordValidationType
import com.example.shoppieclient.domain.auth.models.signin.SignInRequest
import com.example.shoppieclient.domain.auth.use_cases.signIn.SignInValidationUseCases
import com.example.shoppieclient.domain.main.use_cases.DataStoreUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject


const val TAG = "LoginViewModel"
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val validationUseCases: SignInValidationUseCases,
    private val shoppieRepo: ShoppieApi,
    private val dataStoreUseCases: DataStoreUseCases
): ViewModel(){
    private val _signInState: MutableState<SignInState> = mutableStateOf(SignInState())
    val signInState = _signInState

    fun onEmailChange(newValue: String) {
        signInState.value = signInState.value.copy(
            emailInput = newValue
        )
        checkEmailInputValidation()
    }

    private fun checkEmailInputValidation() {
        val emailValidationResult =
            validationUseCases.validationEmailUseCase(email = signInState.value.emailInput)
        processEmailInputValidationType(emailValidationResult)
    }


    private fun processEmailInputValidationType(emailValidationResult: SignInEmailValidationType) {
        signInState.value = when (emailValidationResult) {
            SignInEmailValidationType.EMPTY_EMAIL -> {
                signInState.value.copy(
                    emailErrorMsgInput = "Please enter an email",
                    isInputValid = false
                )
            }

            SignInEmailValidationType.INVALID_EMAIL -> {
                signInState.value.copy(
                    emailErrorMsgInput = "Please enter a valid email",
                    isInputValid = false
                )
            }

            SignInEmailValidationType.VALID_EMAIL -> {
                signInState.value.copy(
                    emailErrorMsgInput = null,
                    isInputValid = true
                )
            }
        }

    }

    fun onPasswordChange(newValue: String) {
        signInState.value = signInState.value.copy(
            passwordInput = newValue
        )
        checkPasswordInputValidation()
    }


    private fun checkPasswordInputValidation() {
        val passwordValidationResult =
            validationUseCases.validationPasswordUseCase(password = signInState.value.passwordInput)
        processPasswordInputValidationType(passwordValidationResult)
    }

    private fun processPasswordInputValidationType(passwordValidationResult: SignInPasswordValidationType) {
        signInState.value = when (passwordValidationResult) {
            SignInPasswordValidationType.EMPTY_PASSWORD -> {
                signInState.value.copy(
                    passwordErrorMsgInput = "Please enter a password",
                    isInputValid = false
                )
            }

            SignInPasswordValidationType.INVALID_PASSWORD -> {
                signInState.value.copy(
                    passwordErrorMsgInput = "Please enter a valid password",
                    isInputValid = false
                )
            }

            SignInPasswordValidationType.VALID_PASSWORD -> {
                signInState.value.copy(
                    passwordErrorMsgInput = null,
                    isInputValid = true
                )
            }
        }

    }

    fun onTogglePasswordVisibility() {
        signInState.value = signInState.value.copy(
            isPasswordShown = !signInState.value.isPasswordShown
        )
    }


    fun onLoginClick() {
        signInState.value = signInState.value.copy(
            isLoading = true
        )

        viewModelScope.launch {

            signInState.value = try {

                val loginResult = shoppieRepo.signIn(
                    SignInRequest(
                        email = signInState.value.emailInput,
                        password = signInState.value.passwordInput
                    )
                )

                Log.e(TAG, loginResult.token)

                dataStoreUseCases.saveTokenUseCase(loginResult.token)

                signInState.value.copy(
                    isSuccessfullyLoggedIn = true,
                    isLoading = false,
                    afterSuccessfullyLoggedIn = loginResult.token,
                    navigateToMain = true
                )


            } catch (e: HttpException) {
                if (e.code() == 400) {
                    signInState.value.copy(
                        errorMsgLoginProcess = e.message(),
                        isLoading = false
                    )
                } else {
                    Log.e(TAG, "onLoginClick: errorr >>>>>>> ${e.message()}")
                    signInState.value.copy(
                        errorMsgLoginProcess = e.message.toString()
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
                signInState.value.copy(
                    errorMsgLoginProcess = e.message
                )
            } finally {
                signInState.value.copy(
                    isLoading = false
                )
            }
        }
    }


}