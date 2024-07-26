package com.example.shoppieclient.presentation.main.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shoppieclient.presentation.main.home.ShoeItem
import com.example.shoppieclient.ui.theme.BackGroundColor
import com.example.shoppieclient.ui.theme.LightGray
import com.example.shoppieclient.ui.theme.PrimaryBlue
import com.example.shoppieclient.ui.theme.TitleColor

@Composable
fun ShoeCard(shoe: ShoeItem) {
    Box(
        modifier = Modifier
            .width(220.dp)
            .height(220.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(LightGray)
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(start = 8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp),
                painter = painterResource(id = shoe.image),
                contentDescription = shoe.name,
                contentScale = ContentScale.FillBounds
            )

                Text(
                    text = "Best Seller",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        color = PrimaryBlue
                    )
                )

            Text(
                text = shoe.name,
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
                Text(text = "$${shoe.price}")
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