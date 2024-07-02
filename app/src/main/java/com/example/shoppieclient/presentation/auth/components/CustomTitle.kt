package com.example.shoppieclient.presentation.auth.components

import android.widget.Space
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.shoppieclient.ui.theme.SubTitleColor
import com.example.shoppieclient.ui.theme.TitleColor

@Composable
fun CustomTitle(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String
) {

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Text(
            text = title, style = MaterialTheme.typography.titleLarge.copy(
                color = TitleColor,
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = subTitle, style = MaterialTheme.typography.titleMedium.copy(
                color = SubTitleColor,
                fontWeight = FontWeight.SemiBold
            )
        )

    }

}