package com.example.shoppieclient.presentation.main.favorite

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.example.shoppieclient.domain.models.ShoppieItem
import com.example.shoppieclient.presentation.common.EmptyScreen
import com.example.shoppieclient.presentation.main.components.CustomNavigationTopAppBar
import com.example.shoppieclient.presentation.main.favorite.components.FavoritesGrid

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(
    scrollBehavior: TopAppBarScrollBehavior,
    onNavigateClick: () -> Unit,
//    favShoppieItems: LazyPagingItems<ShoppieItem>,
    favShoppieItems: List<ShoppieItem>,
    onItemClick: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
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

            FavoritesGrid(
                favItems = favShoppieItems,
                onItemClick = { onItemClick() }
            )

            if(favShoppieItems.isEmpty()) {
                EmptyScreen(
                    modifier = Modifier.fillMaxSize().padding(16.dp),
                    title = "No Favorite items found",
                    subtitle = "Give love to your favorite images"
                )
            }



        }

    }
}