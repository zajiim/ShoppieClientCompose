package com.example.shoppieclient.presentation.auth.signin

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppieclient.data.remote.api.ShoppieApi
import com.example.shoppieclient.domain.auth.models.signin.EmailValidationType
import com.example.shoppieclient.domain.auth.models.signin.PasswordValidationType
import com.example.shoppieclient.domain.auth.models.signin.SignInRequest
import com.example.shoppieclient.domain.auth.use_cases.signIn.SignInValidationUseCases
import com.example.shoppieclient.domain.main.use_cases.DataStoreUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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
    private val _userCartItemCount = MutableStateFlow(0)
    val userCartItemCount: StateFlow<Int> = _userCartItemCount
    fun onEmailChange(newValue: String) {
        signInState.value = signInState.value.copy(
            emailInput = newValue
        )
        checkEmailInputValidation()
    }

    private fun checkEmailInputValidation() {
        val emailValidationResult =
            validationUseCases.signInValidationEmailUseCase(email = signInState.value.emailInput)
        processEmailInputValidationType(emailValidationResult)
    }


    private fun processEmailInputValidationType(emailValidationResult: EmailValidationType) {
        signInState.value = when (emailValidationResult) {
            EmailValidationType.EMPTY_EMAIL -> {
                signInState.value.copy(
                    emailErrorMsgInput = "Please enter an email",
                    isInputValid = false
                )
            }

            EmailValidationType.INVALID_EMAIL -> {
                signInState.value.copy(
                    emailErrorMsgInput = "Please enter a valid email",
                    isInputValid = false
                )
            }

            EmailValidationType.VALID_EMAIL -> {
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
            validationUseCases.signInValidationPasswordUseCase(password = signInState.value.passwordInput)
        processPasswordInputValidationType(passwordValidationResult)
    }

    private fun processPasswordInputValidationType(passwordValidationResult: PasswordValidationType) {
        signInState.value = when (passwordValidationResult) {
            PasswordValidationType.EMPTY_PASSWORD -> {
                signInState.value.copy(
                    passwordErrorMsgInput = "Please enter a password",
                    isInputValid = false
                )
            }

            PasswordValidationType.INVALID_PASSWORD -> {
                signInState.value.copy(
                    passwordErrorMsgInput = "Please enter a valid password",
                    isInputValid = false
                )
            }

            PasswordValidationType.VALID_PASSWORD -> {
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


    fun onSignInClick() {
        signInState.value = signInState.value.copy(
            isLoading = true
        )

        viewModelScope.launch {

            signInState.value = try {
                Log.e(TAG, "onLoginClick: >>>>>>>>", )

                val loginResult = shoppieRepo.signIn(
                    SignInRequest(
                        email = signInState.value.emailInput,
                        password = signInState.value.passwordInput
                    )
                )

                Log.e(TAG, "after onLoginClick: >>>>>>>>${loginResult.cart.size}", )
                Log.e(TAG, loginResult.token)

                dataStoreUseCases.saveTokenUseCase(loginResult.token)

                Log.e(TAG, "after datastore: >>>>>>>>", )

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
                signInState.value = signInState.value.copy(
                    isLoading = false
                )
            }
        }
    }



}