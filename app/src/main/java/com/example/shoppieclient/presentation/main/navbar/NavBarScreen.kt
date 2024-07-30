package com.example.shoppieclient.presentation.main.navbar

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.shoppieclient.core.navigation.BottomNavGraph
import com.example.shoppieclient.presentation.main.home.components.BottomNavBar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavBarScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        bottomBar = {
            BottomNavBar(
                navController = navController
            )
        },
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { innerPadding ->
        BottomNavGraph(
            modifier = Modifier
                .fillMaxSize(),
            navController = navController,
            scrollBehavior = scrollBehavior,
            bottomPadding = innerPadding
        )
    }
}