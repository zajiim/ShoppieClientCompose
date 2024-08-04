package com.example.shoppieclient.presentation.main.home.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.shoppieclient.R
import com.example.shoppieclient.domain.models.ShoppieItem
import com.example.shoppieclient.presentation.main.home.ShoeItem
import com.example.shoppieclient.ui.theme.LightGray
import com.example.shoppieclient.ui.theme.PrimaryBlue
import com.example.shoppieclient.ui.theme.TitleColor
import com.example.shoppieclient.utils.shimmerEffect
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield
import org.jetbrains.annotations.Async

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NewArrivals(
    modifier: Modifier = Modifier,
    leadingTitle: String,
    trailingTitle: String,
    shoes: List<ShoppieItem>,
    isLoading: Boolean
) {
    val pagerState = rememberPagerState(pageCount = {shoes.size})
    val scope = rememberCoroutineScope()

    LaunchedEffect(pagerState) {
        scope.launch {
            while (isActive) {
                delay(3000)
                val nextPage = (pagerState.currentPage + 1) % pagerState.pageCount
                pagerState.animateScrollToPage(
                    page = nextPage,
                    animationSpec = tween(600)
                )
            }
        }
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth().padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = leadingTitle, style = TextStyle(
                    color = TitleColor, fontSize = 16.sp, fontWeight = FontWeight.Medium
                )
            )
            Text(
                text = trailingTitle, style = TextStyle(
                    color = PrimaryBlue, fontSize = 12.sp, fontWeight = FontWeight((300))
                )
            )
        }
        
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .height(120.dp)
        ) { pageIndex ->


            if(isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Gray.copy(alpha = 0.1f))
                        .shimmerEffect()
                )
            } else {
                val shoe = shoes[pageIndex]
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(LightGray),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.SpaceEvenly,
                    ) {
                        Text(
                            text = "Best Seller",
                            style = TextStyle(
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Normal,
                                color = PrimaryBlue
                            )
                        )
                        Text(
                            text = shoe?.name ?: "",
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                color = TitleColor
                            )
                        )
                        Text(text = "$${shoe.price}")
                    }
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(140.dp)
                            .padding(16.dp),
                        model = shoe.images?.get(0),
                        contentDescription = shoe.name,
                        contentScale = ContentScale.Fit
                    )
                }
            }
        }
        Row(
            Modifier
                .height(50.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pagerState.pageCount) { index ->
                val isSelected = pagerState.currentPage == index
                val width by animateDpAsState(
                    targetValue = if (isSelected) 16.dp else 4.dp,
                    animationSpec = tween(300), label = "width"
                )

                val color by animateColorAsState(
                    targetValue = if (isSelected) PrimaryBlue else LightGray,
                    animationSpec = tween(300), label = "color"
                )
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .width(width)
                        .height(4.dp)
                        .clip(CircleShape)
                        .background(color)

                )
            }
        }


    }


}