package com.example.shoppieclient.core.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.shoppieclient.presentation.auth.signin.LoginScreen
import com.example.shoppieclient.presentation.auth.signup.SignUpScreen


fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.AUTH,
        startDestination = AuthScreen.Login.route
    ) {
        composable(route = AuthScreen.Login.route) {
            //login screen
            LoginScreen(navController = navController)
        }

        composable(route = AuthScreen.SignUp.route) {
            SignUpScreen(navController = navController)
        }

        composable(route = AuthScreen.ForgotPassword.route) {
            //forgot password screen
        }
    }
}