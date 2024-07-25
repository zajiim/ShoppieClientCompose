package com.example.shoppieclient.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.shoppieclient.presentation.home.BottomBarScreen
import com.example.shoppieclient.utils.bottomNavClip


@Composable
fun BottomNavBar(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Home2,
        BottomBarScreen.Stats,
        BottomBarScreen.Profile,
        BottomBarScreen.Profile2,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Box(modifier = modifier) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .bottomNavClip(56.dp, 16.dp)
                    .background(Color.White),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                screens.forEach { item ->
                    CustomNavBarItem(
                        icon = if (currentRoute == item.route) item.selectedIcon else item.unSelectedIcon,
                        title = item.title,
                        route = item.route,
                        onRouteClicked = { route ->
                            navController.navigate(route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        selected = currentRoute == item.route
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Color.Gray)
                .align(alignment = Alignment.TopCenter)
                .clickable { },
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.size(24.dp),
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = null
            )
        }
    }
}