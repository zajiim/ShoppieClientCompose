package com.example.shoppieclient.presentation.main.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.shoppieclient.domain.models.ShoppieItem
import com.example.shoppieclient.presentation.main.home.ShoeItem
import com.example.shoppieclient.ui.theme.BackGroundColor
import com.example.shoppieclient.ui.theme.LightGray
import com.example.shoppieclient.ui.theme.PrimaryBlue
import com.example.shoppieclient.ui.theme.TitleColor
import com.example.shoppieclient.utils.shimmerEffect
import org.jetbrains.annotations.Async

@Composable
fun ShoeCard(
    shoe: ShoppieItem ?= null,
    isLoading: Boolean,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .width(220.dp)
            .height(220.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(LightGray)
            .clickable{ onClick() }
    ) {
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Gray.copy(alpha = 0.1f))
                    .shimmerEffect()
            )
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {

                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp),
                    model = shoe?.images?.get(0),
                    contentDescription = shoe?.name,
                    contentScale = ContentScale.FillBounds
                )

                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = "Best Seller",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        color = PrimaryBlue
                    )
                )

                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = shoe?.name ?: "",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = TitleColor
                    )
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier.padding(start = 8.dp),
                        text = "$${shoe?.price}"
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(topStart = 16.dp))
                            .background(PrimaryBlue)
                    ) {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Add to cart",
                                tint = White
                            )
                        }
                    }
                }
            }
        }

    }
}