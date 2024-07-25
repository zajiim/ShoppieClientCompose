package com.example.shoppieclient.presentation.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Home
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

    data object Home2: BottomBarScreen(
        title = "Home",
        route = "home",
        selectedIcon = Icons.Filled.Close,
        unSelectedIcon = Icons.Outlined.Close
    )

    data object Stats: BottomBarScreen(
        title = "Stats",
        route = "stats",
        selectedIcon = null,
        unSelectedIcon = null
    )

    data object Profile: BottomBarScreen(
        title = "Profile",
        route = "profile",
        selectedIcon = Icons.Filled.Person,
        unSelectedIcon = Icons.Outlined.Person
    )

    data object Profile2: BottomBarScreen(
        title = "Home",
        route = "home",
        selectedIcon = Icons.Filled.DateRange,
        unSelectedIcon = Icons.Outlined.DateRange
    )
}