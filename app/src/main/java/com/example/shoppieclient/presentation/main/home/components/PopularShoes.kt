package com.example.shoppieclient.presentation.main.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shoppieclient.R
import com.example.shoppieclient.domain.models.ShoppieItem
import com.example.shoppieclient.presentation.main.home.ShoeItem
import com.example.shoppieclient.ui.theme.PrimaryBlue
import com.example.shoppieclient.ui.theme.TitleColor

@Composable
fun PopularShoes(
    modifier: Modifier = Modifier,
    leadingTitle: String,
    trailingTitle: String,
    shoes: List<ShoppieItem>,
    isLoading: Boolean
) {
    Column(
        modifier = modifier
        ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = leadingTitle, style = TextStyle(
                    color = TitleColor, fontSize = 16.sp, fontWeight = FontWeight.Medium
                )
            )
            Text(
                text = trailingTitle, style = TextStyle(
                    color = PrimaryBlue, fontSize = 12.sp, fontWeight = FontWeight((300))
                )
            )
        }

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {

            if (isLoading) {
                items(2) {
                    ShoeCard(
                        isLoading = true
                    )
                }
            } else {
                items(shoes) { shoe ->
                    ShoeCard(
                        shoe = shoe,
                        isLoading = false
                    )
                }
            }
        }

    }

}

