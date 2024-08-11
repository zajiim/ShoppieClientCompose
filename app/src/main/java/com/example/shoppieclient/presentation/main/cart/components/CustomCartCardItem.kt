package com.example.shoppieclient.presentation.main.cart.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.shoppieclient.domain.auth.models.cart.Products
import com.example.shoppieclient.domain.models.ShoppieItem

@Composable
fun CustomCartCardItem(
    modifier: Modifier = Modifier,
    cartItem: Products
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxHeight(),
            model = cartItem.images?.get(0) ?: "",
            contentDescription = cartItem.name,
            contentScale = ContentScale.FillHeight,
        )

        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = cartItem.name, style = TextStyle(
                    fontSize = 16.sp, color = Color.Black
                )
            )
            Text(
                text = "$${cartItem.price}", style = TextStyle(
                    fontSize = 14.sp, color = Color.Black
                )
            )

            CustomItemCounter()
        }
        Spacer(modifier = Modifier.weight(1f))

        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
            ) {
            Text(
                text = "L", style = TextStyle(
                    fontSize = 16.sp, color = Color.Black
                )
            )

            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Outlined.Delete,
                    contentDescription = "Delete product"
                )
            }
        }
    }

}