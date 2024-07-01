package com.example.shoppieclient.core.navigation

sealed class AuthScreen(val route: String) {
    data object Login: AuthScreen("login")
    data object SignUp: AuthScreen("signup")
    data object ForgotPassword: AuthScreen("forgot_password")
}


object Graph {
    const val ROOT = "root_graph"
    const val AUTH = "auth_graph"
    const val ON_BOARDING = "on_boarding_graph"
    const val MAIN = "main_graph"
    const val ADD_PRODUCT = "add_product_graph"
}