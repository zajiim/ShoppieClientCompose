package com.example.shoppieclient.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.shoppieclient.presentation.home.BottomBarScreen
import com.example.shoppieclient.presentation.home.HomeScreen
import com.example.shoppieclient.presentation.profile.ProfileScreen
import com.example.shoppieclient.presentation.stats.StatsScreen

@Composable
fun MainNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route,
        route = Graph.MAIN
    ) {
        composable(BottomBarScreen.Home.route) {
            HomeScreen(modifier = modifier)
        }
        composable(BottomBarScreen.Home2.route) {
            HomeScreen(modifier = modifier)
        }
        composable(BottomBarScreen.Stats.route) {
            StatsScreen(modifier = modifier)
        }
        composable(BottomBarScreen.Profile.route) {
            ProfileScreen(modifier = modifier)
        }
        composable(BottomBarScreen.Profile2.route) {
            ProfileScreen(modifier = modifier)
        }
    }
}