package com.example.shoppieclient.presentation.main.navbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val title: String,
    val route: String,
    val selectedIcon: ImageVector? = null,
    val unSelectedIcon: ImageVector? = null,
) {
    data object Home: BottomBarScreen(
        title = "Home",
        route = "home",
        selectedIcon = Icons.Filled.Home,
        unSelectedIcon = Icons.Outlined.Home
    )

    data object Favorite: BottomBarScreen(
        title = "Favorite",
        route = "favorite",
        selectedIcon = Icons.Filled.Favorite,
        unSelectedIcon = Icons.Outlined.FavoriteBorder
    )

    data object Cart: BottomBarScreen(
        title = "Cart",
        route = "cart",
        selectedIcon = null,
        unSelectedIcon = null
    )

    data object Notification: BottomBarScreen(
        title = "Notification",
        route = "notification",
        selectedIcon = Icons.Filled.Notifications,
        unSelectedIcon = Icons.Outlined.Notifications
    )

    data object Profile: BottomBarScreen(
        title = "Profile",
        route = "profile",
        selectedIcon = Icons.Filled.Person,
        unSelectedIcon = Icons.Outlined.Person
    )
}