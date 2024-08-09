package com.example.shoppieclient.presentation.main.navbar

import android.annotation.SuppressLint
import android.util.Log
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.shoppieclient.core.navigation.BottomNavGraph
import com.example.shoppieclient.presentation.auth.signin.LoginViewModel
import com.example.shoppieclient.presentation.main.home.components.BottomNavBar


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun NavBarScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
//    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    val loginViewModel: LoginViewModel = hiltViewModel()
    val cartCount by loginViewModel.userCartItemCount.collectAsState()
    Log.e("tag_cart_count", "$cartCount: ", )
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