package com.example.shoppieclient.presentation.main.cart.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.shoppieclient.domain.models.ShoppieItem

@Composable
fun CustomCartCardList(
    modifier: Modifier = Modifier,
    shoppieItems: List<ShoppieItem>
) {
    
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        items(shoppieItems) { shoppieItem ->
            CustomCartCardItem(shoppieItem = shoppieItem)
        }
    }


    

}