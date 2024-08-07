package com.example.shoppieclient.presentation.main.details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shoppieclient.utils.euList
import com.example.shoppieclient.utils.ukList
import com.example.shoppieclient.utils.usaList

@Composable
fun CustomSizeSection(
    modifier: Modifier = Modifier
) {
    var selectedRegion by remember { mutableStateOf("EU") }
    var selectedSize by remember { mutableStateOf<Double?>(null) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Size", style = TextStyle(
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                RegionButtons(
                    region = "EU",
                    selectedRegion = selectedRegion
                ) {
                    selectedRegion = "EU"
                }

                RegionButtons(
                    region = "US",
                    selectedRegion = selectedRegion
                ) {
                    selectedRegion = "US"
                }

                RegionButtons(
                    region = "UK",
                    selectedRegion = selectedRegion
                ) {
                    selectedRegion = "UK"
                }
            }

        }
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val sizes = when(selectedRegion) {
                "EU" -> euList
                "US" -> usaList
                "UK" -> ukList
                else -> emptyList()
            }
            items(sizes) { size ->
                SizeButtons(
                    size = size,
                    isSelected =  selectedSize == size) {
                    selectedSize = size
                }
            }

        }
    }

}

@Preview(showBackground = true)
@Composable
private fun CustomPreview() {
    CustomSizeSection()
}