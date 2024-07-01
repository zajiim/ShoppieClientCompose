package com.example.shoppieclient.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shoppieclient.presentation.auth.main.MainScreen
import com.example.shoppieclient.presentation.on_boarding.OnboardingScreen

@Composable
fun RootNavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.ON_BOARDING
    ) {

        composable(Graph.ON_BOARDING) {
            OnboardingScreen(navController)
        }

        authNavGraph(navController)

        composable(route = Graph.MAIN) {
            MainScreen()
        }

    }

}