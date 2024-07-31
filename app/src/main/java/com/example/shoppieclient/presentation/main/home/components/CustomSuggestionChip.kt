package com.example.shoppieclient.presentation.main.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.shoppieclient.ui.theme.PrimaryBlue

@Composable
fun CustomSuggestionChip(
    brand: String,
    iconResId: Int,
    isExpanded: Boolean,
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(40.dp),
        color = if (isExpanded) PrimaryBlue else Color.White
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = iconResId),
                contentDescription = "$brand logo",
                modifier = Modifier.size(24.dp)
            )
            if (isExpanded) {
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = brand,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }
    }
}