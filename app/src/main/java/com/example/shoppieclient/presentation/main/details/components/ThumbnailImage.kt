package com.example.shoppieclient.presentation.main.details.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.shoppieclient.ui.theme.PrimaryBlue

@Composable
fun ThumbnailImage(
    modifier: Modifier = Modifier, imageUrl: String, isSelected: Boolean, onClick: () -> Unit
) {
    val borderColor by animateColorAsState(
        targetValue = if (isSelected) PrimaryBlue else Color.Transparent,
        label = "border color",
        animationSpec = tween(300)
    )
    Card(
        onClick = onClick, modifier = Modifier
            .size(64.dp)
            .padding(4.dp)
            .border(
                width = 2.dp, color = borderColor, shape = RoundedCornerShape(8.dp)
            )
    ) {

        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(8.dp))
        )

    }

}