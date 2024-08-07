package com.example.shoppieclient.presentation.main.details

import android.widget.Toast
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.FavoriteBorder
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shoppieclient.presentation.main.components.CustomNavigationTopAppBar
import com.example.shoppieclient.presentation.main.details.components.AddCartBottomSection
import com.example.shoppieclient.presentation.main.details.components.CustomSizeSection
import com.example.shoppieclient.presentation.main.details.components.ProductImage
import com.example.shoppieclient.presentation.main.details.components.ThumbnailImage
import com.example.shoppieclient.ui.theme.BackGroundColor
import com.example.shoppieclient.ui.theme.PrimaryBlue
import com.example.shoppieclient.ui.theme.SubTitleColor
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
    onNavigateClick: () -> Unit,
    onCartClick: () -> Unit,
    viewModel: DetailsViewModel,
    bottomPadding: PaddingValues
) {
    val productDetailsState by viewModel.productDetails.collectAsState()
    val pagerState = rememberPagerState(pageCount = { productDetailsState.data?.images?.size ?: 0 })
    var selectedImageIndex by remember { mutableIntStateOf(0) }
    val scope = rememberCoroutineScope()
    var expanded by remember { mutableStateOf(false) }
    val maxLines by animateIntAsState(
        targetValue = if (expanded) Int.MAX_VALUE else 3,
        label = "max lines",
        animationSpec = tween(300)
    )
    val ctx = LocalContext.current

    var selectedRegion by remember { mutableStateOf("EU") }
    var selectedSize by remember { mutableIntStateOf(40) }

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            selectedImageIndex = page
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(BackGroundColor)
            .nestedScroll(scrollBehavior.nestedScrollConnection)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            productDetailsState.data?.images?.let { images ->
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                ) { page ->
                    ProductImage(
                        modifier = Modifier,
                        imageUrl = images[page]
                    )
                }
            }

            productDetailsState.data?.category?.let {
                Text(
                    text = it, style = TextStyle(
                        color = PrimaryBlue,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }


            productDetailsState.data?.name?.let {
                Text(
                    text = it, style = TextStyle(
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }

            productDetailsState.data?.price?.let {
                Text(
                    text = "₹ $it", style = TextStyle(
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    ),
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }

            productDetailsState.data?.description?.let {
                Text(text = it,
                    style = TextStyle(
                        color = SubTitleColor,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal
                    ),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = maxLines,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .clickable { expanded = !expanded })
            }


            Text(
                text = "Gallery", style = TextStyle(
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                ),
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            LazyRow(
                modifier = Modifier
                    .height(80.dp)
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 16.dp)
            ) {
                productDetailsState.data?.images?.let { images ->
                    itemsIndexed(images) { index, item ->
                        ThumbnailImage(
                            imageUrl = item,
                            isSelected = selectedImageIndex == index,
                            onClick = {
                                selectedImageIndex = index
                                scope.launch {
                                    pagerState.animateScrollToPage(index)
                                }
                            }
                        )
                    }
                }
            }

            CustomSizeSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                selectedRegion = selectedRegion,
                selectedSize = selectedSize,
                onRegionSelected = {
                    selectedRegion = it
                },
                onSizeSelected = {
                    selectedSize = it
                }
            )

            Spacer(modifier = Modifier.height(140.dp))
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
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Outlined.FavoriteBorder,
                        contentDescription = "Favorite icon"
                    )
                }
            },
            scrollBehavior = scrollBehavior
        )

        AddCartBottomSection(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .wrapContentSize(Alignment.BottomCenter)
                .padding(bottom = bottomPadding.calculateBottomPadding()),
            price = productDetailsState.data?.price.toString(),
            selectedRegion = selectedRegion,
            selectedSize = selectedSize,
            onAddToCartClick = { region, size ->
                selectedRegion = region
                selectedSize = size
                Toast.makeText(ctx, "$region and $size", Toast.LENGTH_SHORT).show()
                onCartClick()
            }
        )

    }
}

