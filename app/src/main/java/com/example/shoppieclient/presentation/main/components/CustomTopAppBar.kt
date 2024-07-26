package com.example.shoppieclient.presentation.main.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior,
    trailingIcon: @Composable () -> Unit = {},
    leadingIcon: @Composable (RowScope.() -> Unit) = {},
    title: String,
    subtitle: String
) {

    CenterAlignedTopAppBar(
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        title = {
            Column {
                Text(text = title, style = TextStyle(fontSize = 14.sp, color = Color.Gray, fontWeight = FontWeight(300)))
                Text(text = subtitle, style = TextStyle(fontSize = 16.sp, color = Color.Black, fontWeight = FontWeight(700)))
            }
        },
        actions = leadingIcon,
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            scrolledContainerColor = MaterialTheme.colorScheme.background
        ),
        navigationIcon = trailingIcon
    )

}