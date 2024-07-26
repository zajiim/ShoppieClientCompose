package com.example.shoppieclient.core.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shoppieclient.presentation.auth.main.MainActivityViewModel
import com.example.shoppieclient.presentation.main.navbar.NavBarScreen
import com.example.shoppieclient.presentation.on_boarding.OnBoardingViewModel
import com.example.shoppieclient.presentation.on_boarding.OnboardingScreen

@Composable
fun RootNavGraph(
    mainActivityViewModel: MainActivityViewModel = hiltViewModel()
) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        route = Graph.ROOT,
//        startDestination = Graph.ON_BOARDING,
        startDestination = mainActivityViewModel.startDestination
    ) {

        composable(Graph.ON_BOARDING) {
            val onBoardingViewModel: OnBoardingViewModel = hiltViewModel()
            OnboardingScreen(
                navController = navController,
                event = onBoardingViewModel::onEvent
            )
        }

        authNavGraph(navController)

        composable(route = Graph.MAIN) {
            NavBarScreen()
        }
    }

}