package com.example.shoppieclient.presentation.main.favorite.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.shoppieclient.domain.models.ShoppieItem
import com.example.shoppieclient.ui.theme.PrimaryBlue
import com.example.shoppieclient.ui.theme.TitleColor

@Composable
fun FavShoppieItem(
    modifier: Modifier = Modifier,
    shoppieItem: ShoppieItem?,
    isFav: Boolean,
    onToggleFavStatus: () -> Unit
) {
    val shoppieImage =
        ImageRequest.Builder(LocalContext.current).data(shoppieItem?.imageUrl).crossfade(true)
            .build()

    Card(
        shape = RoundedCornerShape(16.dp), modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
    ) {

        Box {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                AsyncImage(
                    modifier = Modifier.fillMaxWidth().height(160.dp),
                    model = shoppieImage,
                    contentDescription = shoppieItem?.productName,
                    contentScale = ContentScale.FillBounds,
                )

                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = shoppieItem?.category ?: "",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        color = PrimaryBlue
                    )
                )

                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = shoppieItem?.productName ?: "",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = TitleColor
                    )
                )

                Text(
                    modifier = Modifier.padding(start = 8.dp, bottom = 12.dp),
                    text = "$${shoppieItem?.price}",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = TitleColor
                    )
                )

            }

            FavButton(
                modifier = Modifier.align(Alignment.TopStart),
                isFavorite = isFav,
                onFavClick = onToggleFavStatus
            )
        }

    }


}