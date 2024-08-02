package com.example.shoppieclient.presentation.main.cart.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.shoppieclient.R
import com.example.shoppieclient.ui.theme.PrimaryBlue

@Composable
fun CustomItemCounter(
    modifier: Modifier = Modifier,
) {
    val count by remember {
        mutableIntStateOf(1)
    }

    Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        Card(
            shape = CircleShape,
            colors = CardDefaults
                .cardColors(
                    containerColor = Color.White
                )
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_minus),
                contentDescription = "decrement",
                modifier = Modifier.clickable {
                    count.plus(1)
                }
            )
        }
        Text(
            text = count.toString()
        )
        Card(
            shape = CircleShape,
            colors = CardDefaults
                .cardColors(
                    containerColor = PrimaryBlue
                )
        ){
            Image(
                painter = painterResource(id = R.drawable.ic_plus),
                colorFilter = ColorFilter.tint(Color.White),
                contentDescription = "increment",
                modifier = Modifier.clickable {
                    count.minus(1)
                }
            )
        }

    }
}