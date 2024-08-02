package com.example.shoppieclient.presentation.main.cart.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.shoppieclient.domain.models.ShoppieItem
import com.example.shoppieclient.ui.theme.PrimaryBlue

@Composable
fun CustomCartCardItem(
    modifier: Modifier = Modifier,
    shoppieItem: ShoppieItem
) {

    Row(
        modifier = modifier.height(80.dp)
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxHeight(),
            model = shoppieItem.images?.get(0),
            contentDescription = shoppieItem.name,
            contentScale = ContentScale.FillHeight,
        )

        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = shoppieItem.name!!, style = TextStyle(
                    fontSize = 16.sp, color = Color.Black
                )
            )
            Text(
                text = "$${shoppieItem.price}", style = TextStyle(
                    fontSize = 14.sp, color = Color.Black
                )
            )

            CustomItemCounter()
        }
    }

}