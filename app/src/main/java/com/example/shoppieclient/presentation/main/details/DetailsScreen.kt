package com.example.shoppieclient.presentation.main.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.shoppieclient.R
import com.example.shoppieclient.presentation.main.components.CustomNavigationTopAppBar
import com.example.shoppieclient.ui.theme.BackGroundColor
import com.example.shoppieclient.utils.Resource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
    onNavigateClick: () -> Unit,
    productId: String,
    viewModel: DetailsViewModel
) {
    val productDetailsState by viewModel.productDetails.collectAsState()

    LaunchedEffect(productId) {
        viewModel.fetchProductDetails(productId)
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(BackGroundColor)
            .nestedScroll(scrollBehavior.nestedScrollConnection)
    ) {
        when (val details = productDetailsState) {
            is Resource.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            is Resource.Success -> {
                val product = details.data
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    AsyncImage(
                        model = product?.images?.firstOrNull(),
                        contentDescription = product?.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(400.dp),
                        contentScale = ContentScale.Fit
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = product?.name ?: "", )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Price: $${product?.price}")
                }
            }
            is Resource.Error -> {
                Text(
                    text = "Error: ${details.message}",
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.Red
                )
            }
        }

        CustomNavigationTopAppBar(
            title = "Details",
            navigationIcon = {
                IconButton(onClick = onNavigateClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = "Go back"
                    )
                }
            },
            actions = {
                IconButton(onClick = {  }) {
                    Icon(
                        imageVector = Icons.Outlined.FavoriteBorder,
                        contentDescription = "Favorite icon"
                    )
                }
            },
            scrollBehavior = scrollBehavior
        )
    }
}

