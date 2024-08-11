package com.example.shoppieclient.presentation.main.cart.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.example.shoppieclient.domain.auth.models.cart.Products
import com.example.shoppieclient.domain.models.ShoppieItem

@Composable
fun CustomCartCardList(
    modifier: Modifier = Modifier,
    cartItems: LazyPagingItems<Products>,
) {
    
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        items(cartItems.itemCount) { cartItem ->
            val cart = cartItems[cartItem]
            if (cart != null) {
                CustomCartCardItem(cartItem = cart)
            }
        }
    }


    

}