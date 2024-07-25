package com.example.shoppieclient.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun CustomNavBarItem(
    modifier: Modifier = Modifier,
    icon: ImageVector?,
    title: String,
    route: String,
    onRouteClicked: (String) -> Unit,
    selected: Boolean
) {
    Column(
        modifier = modifier
            .height(120.dp)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {

                onRouteClicked(route)
            }
            .padding(vertical = 8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (icon != null) {
            Image(
                modifier = Modifier.size(24.dp),
                imageVector = icon,
                contentDescription = title
            )
        } else {
            Spacer(
                modifier = Modifier.size(24.dp),
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = title,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodySmall
        )
    }
}
