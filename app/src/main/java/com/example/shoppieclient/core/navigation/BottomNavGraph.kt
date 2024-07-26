package com.example.shoppieclient.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.shoppieclient.presentation.main.cart.CartScreen
import com.example.shoppieclient.presentation.main.navbar.BottomBarScreen
import com.example.shoppieclient.presentation.main.home.HomeScreen
import com.example.shoppieclient.presentation.main.profile.ProfileScreen
import com.example.shoppieclient.presentation.main.favorite.FavoriteScreen
import com.example.shoppieclient.presentation.main.notifications.NotificationsScreen

@Composable
fun BottomNavGraph(
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
        composable(BottomBarScreen.Favorite.route) {
            FavoriteScreen(modifier = modifier)
        }
        composable(BottomBarScreen.Cart.route) {
            CartScreen(modifier = modifier)
        }
        composable(BottomBarScreen.Notification.route) {
            NotificationsScreen(modifier = modifier)
        }
        composable(BottomBarScreen.Profile.route) {
            ProfileScreen(modifier = modifier)
        }
    }
}