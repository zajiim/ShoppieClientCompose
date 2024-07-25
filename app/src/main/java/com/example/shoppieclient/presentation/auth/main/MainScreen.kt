package com.example.shoppieclient.presentation.auth.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.shoppieclient.core.navigation.MainNavGraph
import com.example.shoppieclient.presentation.home.components.BottomNavBar


@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    Scaffold(bottomBar = {
        BottomNavBar(navController = navController, modifier = modifier)
    }) { paddingValues ->
        MainNavGraph(
            modifier = modifier.padding(paddingValues),
            navController = navController
        )
    }
}