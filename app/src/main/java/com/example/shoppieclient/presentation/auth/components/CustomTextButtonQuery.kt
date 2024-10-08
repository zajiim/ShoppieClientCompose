package com.example.shoppieclient.presentation.auth.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.shoppieclient.ui.theme.SubTitleColor

@Composable
fun CustomTextButtonQuery(
    modifier: Modifier = Modifier,
    title: String, clickableText: String,
    onClick: () -> Unit
) {

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        Text(text = title,
            fontSize = 14.sp, color = SubTitleColor, fontWeight = FontWeight.Normal)
        Text(
            modifier = modifier.clickable {
                onClick()
            },
            text = clickableText,
            fontSize = 14.sp,
            color = SubTitleColor,
            fontWeight = FontWeight.Bold
        )

    }

}