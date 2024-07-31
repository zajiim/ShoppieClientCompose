package com.example.shoppieclient.presentation.main.favorite.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.example.shoppieclient.domain.models.ShoppieItem

@Composable
fun FavoritesGrid(
    modifier: Modifier = Modifier,
//    favItems: LazyPagingItems<ShoppieItem>,
    favItems: List<ShoppieItem>,
    onItemClick: (String) -> Unit,
    ) {
    LazyVerticalStaggeredGrid(
        modifier = modifier,
        columns = StaggeredGridCells.Adaptive(120.dp),
        contentPadding = PaddingValues(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalItemSpacing = 8.dp
    ) {
        items(count = favItems.size) { index: Int ->
            val shoppieItem = favItems[index]

            FavShoppieItem(
                shoppieItem = shoppieItem,
                isFav = true,
                onToggleFavStatus = {}
            )
        }

    }

}