package com.example.shoppieclient.presentation.main.favorite

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.shoppieclient.presentation.main.components.CustomNavigationTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(
    scrollBehavior: TopAppBarScrollBehavior,
    onNavigateClick: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            CustomNavigationTopAppBar(
                scrollBehavior = scrollBehavior,
                title = "Favorite",
                navigationIcon = {
                    IconButton(onClick = { onNavigateClick() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = "go back"
                        )
                    }
                },
                actions = {
                    Icon(
                        imageVector = Icons.Outlined.FavoriteBorder,
                        contentDescription = "Favorite icon"
                    )
                }
            )



        }

    }
}