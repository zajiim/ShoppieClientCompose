package com.example.shoppieclient.presentation.on_boarding.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.shoppieclient.ui.theme.DarkGray
import com.example.shoppieclient.ui.theme.LightGray
import com.example.shoppieclient.ui.theme.PrimaryBlue
import com.example.shoppieclient.utils.dpToPx
import com.example.shoppieclient.utils.pxToDp

@Composable
fun CustomPagerIndicator(
    pageCount: Int,
    currentPage: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.wrapContentWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        repeat(pageCount) {
            Indicators(
                isSelected = it == currentPage,
                modifier = Modifier.padding(horizontal = 1.pxToDp())
            )
        }

    }

}

@Composable
fun Indicators(
    isSelected: Boolean, modifier: Modifier
) {

    val dotWidth =
        animateDpAsState(targetValue = if (isSelected) 24.dp else 6.dp, label = "dot width")

    Box(
        modifier = modifier
            .padding(2.dp)
            .size(width = dotWidth.value, height = 6.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(if (isSelected) PrimaryBlue else LightGray)
    )

}