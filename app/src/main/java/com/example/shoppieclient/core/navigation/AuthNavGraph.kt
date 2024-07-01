package com.example.shoppieclient.core.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation


fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.AUTH,
        startDestination = AuthScreen.Login.route
    ) {
        composable(route = AuthScreen.Login.route) {
            //login screen
        }

        composable(route = AuthScreen.SignUp.route) {
            //signup screen
        }

        composable(route = AuthScreen.ForgotPassword.route) {
            //forgot password screen
        }
    }
}