package com.example.shoppieclient.core.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.shoppieclient.presentation.main.cart.CartScreen
import com.example.shoppieclient.presentation.main.navbar.BottomBarScreen
import com.example.shoppieclient.presentation.main.home.HomeScreen
import com.example.shoppieclient.presentation.main.profile.ProfileScreen
import com.example.shoppieclient.presentation.main.favorite.FavoriteScreen
import com.example.shoppieclient.presentation.main.home.HomeViewModel
import com.example.shoppieclient.presentation.main.notifications.NotificationsScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    scrollBehavior: TopAppBarScrollBehavior
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = BottomBarScreen.Home.route,
        route = Graph.MAIN
    ) {
        composable(BottomBarScreen.Home.route) {
            val homeViewModel: HomeViewModel = hiltViewModel()
            HomeScreen(
                modifier = modifier,
                scrollBehavior = scrollBehavior,
                onSearch = homeViewModel::searchItems,
                onChipSelected = homeViewModel::searchItems
            )
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