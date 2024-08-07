package com.example.shoppieclient.core.navigation

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.shoppieclient.presentation.main.cart.CartScreen
import com.example.shoppieclient.presentation.main.cart.CartViewModel
import com.example.shoppieclient.presentation.main.details.DetailsScreen
import com.example.shoppieclient.presentation.main.details.DetailsViewModel
import com.example.shoppieclient.presentation.main.favorite.FavoriteScreen
import com.example.shoppieclient.presentation.main.favorite.FavoriteViewModel
import com.example.shoppieclient.presentation.main.home.HomeScreen
import com.example.shoppieclient.presentation.main.home.HomeViewModel
import com.example.shoppieclient.presentation.main.navbar.BottomBarScreen
import com.example.shoppieclient.presentation.main.notifications.NotificationsScreen
import com.example.shoppieclient.presentation.main.profile.ProfileScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    bottomPadding: PaddingValues
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = BottomBarScreen.Home.route,
        route = Graph.MAIN
    ) {
        composable(BottomBarScreen.Home.route) {
            val homeViewModel: HomeViewModel = hiltViewModel()
            val homeScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
            HomeScreen(
                modifier = modifier,
                scrollBehavior = homeScrollBehavior,
                bottomPadding = bottomPadding,
                viewModel = homeViewModel,
                onNavigateToDetails = { itemId ->
                    Log.e("tag_navigation", "itemid -----> $itemId ")
                    navController.navigate("details/$itemId")
                }
            )
        }
        composable(BottomBarScreen.Favorite.route) {
            val favViewModel: FavoriteViewModel = hiltViewModel()
            val favoriteScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
            FavoriteScreen(
                scrollBehavior = favoriteScrollBehavior,
                onNavigateClick = {
                navController.navigateUp()
            },
                favShoppieItems = favViewModel.favShoppieItems,
                onItemClick = {}
            )
        }
        composable(BottomBarScreen.Cart.route) {
            val cartViewModel: CartViewModel = hiltViewModel()
            val cartScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
            CartScreen(
                modifier = modifier,
                scrollBehavior = cartScrollBehavior,
                cartViewModel = cartViewModel,
                onItemClick = {},
                onNavigateClick = { navController.navigateUp() })
        }
        composable(BottomBarScreen.Notification.route) {
            NotificationsScreen(modifier = modifier)
        }
        composable(BottomBarScreen.Profile.route) {
            ProfileScreen(modifier = modifier)
        }

        composable(
            route = Graph.DETAILS,
            arguments = listOf(navArgument("itemId") { NavType.StringType })
        ) {
            val detailsViewModel: DetailsViewModel = hiltViewModel()
            DetailsScreen(
                onNavigateClick = { navController.navigateUp() },
                viewModel = detailsViewModel,
                bottomPadding = bottomPadding
            )
        }


    }
}