package com.example.shoppieclient.presentation.main.navbar

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.shoppieclient.core.navigation.BottomNavGraph
import com.example.shoppieclient.presentation.main.home.components.BottomNavBar


@Composable
fun NavBarScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    Scaffold(bottomBar = {
        BottomNavBar(navController = navController, modifier = modifier)
    }) { paddingValues ->
        BottomNavGraph(
            modifier = modifier.padding(paddingValues),
            navController = navController
        )
    }
}