package com.example.shoppieclient.presentation.main.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import com.example.shoppieclient.presentation.main.components.CustomTopAppBar
import com.example.shoppieclient.presentation.main.home.components.CustomSuggestionChip
import com.example.shoppieclient.presentation.main.home.components.NewArrivals
import com.example.shoppieclient.presentation.main.home.components.PopularShoes
import com.example.shoppieclient.ui.theme.BackGroundColor
import com.example.shoppieclient.utils.searchKeys

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior,
    onSearch: (String) -> Unit,
    onChipSelected: (String) -> Unit
) {
    var query by remember {
        mutableStateOf("")
    }

    var selectedChip by remember { mutableStateOf(searchKeys.keys.first()) }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .background(BackGroundColor),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            CustomTopAppBar(
                scrollBehavior = scrollBehavior,
                title = "Store location",
                subtitle = "New Delhi",
                trailingIcon = {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Default.Menu, contentDescription = "menu"
                        )
                    }
                },
                leadingIcon = {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Default.ShoppingCart, contentDescription = "cart"
                        )
                    }
                },
            )
        }
        item {
            SearchBar(modifier = Modifier.padding(horizontal = 16.dp),
                query = query,
                onQueryChange = { query = it },
                placeholder = {
                    Text(text = "Looking for shoes")
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search, contentDescription = "search"
                    )
                },
                onSearch = { onSearch(query) },
                active = false,
                onActiveChange = {},
                content = {})
        }

        item {
            LazyRow(
                contentPadding = PaddingValues(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(searchKeys.toList()) { (brandName, brandIcon) ->
                    CustomSuggestionChip(brand = brandName,
                        iconResId = brandIcon,
                        isExpanded = selectedChip == brandName,
                        onClick = {
                            selectedChip = brandName
                            onChipSelected(brandName)
                        })

                }

            }

            Spacer(modifier = Modifier.height(16.dp))

            PopularShoes(
                modifier = Modifier.fillMaxWidth(),
                leadingTitle = "Popular Shoes",
                trailingTitle = "See more",
                shoes = shoesList
            )

            Spacer(modifier = Modifier.height(16.dp))
            

            NewArrivals(
                modifier = Modifier.fillMaxWidth(),
                leadingTitle = "New Arrivals",
                trailingTitle = "See more",
                shoes = shoesList
            )


            Spacer(modifier = Modifier.height(120.dp))
        }


    }


}