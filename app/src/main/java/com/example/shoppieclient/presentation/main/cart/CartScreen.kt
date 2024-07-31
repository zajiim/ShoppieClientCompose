package com.example.shoppieclient.presentation.main.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.shoppieclient.domain.models.ShoppieItem
import com.example.shoppieclient.presentation.common.EmptyScreen
import com.example.shoppieclient.presentation.main.components.CustomNavigationTopAppBar
import com.example.shoppieclient.presentation.main.favorite.components.FavoritesGrid

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior,
    onNavigateClick: () -> Unit,
    onItemClick: () -> Unit,
    myCartItems: List<ShoppieItem>
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            CustomNavigationTopAppBar(
                scrollBehavior = scrollBehavior,
                title = "My Cart",
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
                        imageVector = Icons.Outlined.ShoppingCart,
                        contentDescription = "My cart icon"
                    )
                }
            )



            if(myCartItems.isEmpty()) {
                EmptyScreen(
                    modifier = Modifier.fillMaxSize().padding(16.dp),
                    title = "Cart is empty",
                    subtitle = "Go shopping"
                )
            }



        }

    }

}