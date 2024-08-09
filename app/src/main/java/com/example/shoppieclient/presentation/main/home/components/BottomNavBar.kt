package com.example.shoppieclient.presentation.main.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.shoppieclient.presentation.main.details.DetailsViewModel
import com.example.shoppieclient.presentation.main.navbar.BottomBarScreen
import com.example.shoppieclient.presentation.main.navbar.components.BadgeCart
import com.example.shoppieclient.ui.theme.PrimaryBlue
import com.example.shoppieclient.utils.bottomNavClip


@Composable
fun BottomNavBar(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    cartItemCount: Int
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val showBottomNav = listOf(
        BottomBarScreen.Home.route,
        BottomBarScreen.Favorite.route,
        BottomBarScreen.Notification.route,
        BottomBarScreen.Profile.route
    ).contains(currentRoute)

    if (showBottomNav) {
        val configuration = LocalConfiguration.current
        val screenWidth = configuration.screenWidthDp.dp
        val itemWidth = screenWidth / 5

        val screens = listOf(
            BottomBarScreen.Home,
            BottomBarScreen.Favorite,
            BottomBarScreen.Cart,
            BottomBarScreen.Notification,
            BottomBarScreen.Profile,
        )

        Box(modifier = modifier) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier
                        .navigationBarsPadding()
                        .fillMaxWidth()
                        .bottomNavClip(56.dp, 20.dp)
                        .background(Color.White),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    screens.forEach { item ->
                        CustomNavBarItem(
                            modifier = Modifier.width(itemWidth),
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
                    .size(54.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .align(alignment = Alignment.TopCenter)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        navController.navigate(BottomBarScreen.Cart.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                contentAlignment = Alignment.Center
            ) {

                val cartIcon = if (currentRoute == BottomBarScreen.Cart.route) {
                    Icons.Filled.ShoppingCart
                } else {
                    Icons.Outlined.ShoppingCart
                }
                Image(
                    modifier = Modifier.size(24.dp),
                    imageVector = cartIcon,
                    contentDescription = "cart"
                )
                if (cartItemCount > 0) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(18.dp)
                                .background(
                                    color = PrimaryBlue,
                                    shape = CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {

                            Text(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .align(Alignment.Center),
                                text = cartItemCount.toString(),
                                color = Color.White,
                                fontSize = 8.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }

            }
        }
    }
}


