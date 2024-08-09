package com.example.shoppieclient.presentation.main.navbar

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.shoppieclient.core.navigation.BottomNavGraph
import com.example.shoppieclient.presentation.main.home.components.BottomNavBar


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun NavBarScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    cartViewModel: NavBarCartViewModel = hiltViewModel()
) {

    val cartCount by cartViewModel.cartCount.collectAsState()
    Log.e("cart_count", "count>>>> $cartCount: ")
    Scaffold(
        bottomBar = {
            BottomNavBar(
                navController = navController,
                cartItemCount = cartCount
            )
        },
//        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
        modifier = Modifier
    ) { innerPadding ->
        BottomNavGraph(
            modifier = Modifier
                .fillMaxSize(),
            navController = navController,
//            scrollBehavior = scrollBehavior,
            bottomPadding = innerPadding
        )
    }
}