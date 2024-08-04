package com.example.shoppieclient.data.mapper

import com.example.shoppieclient.data.remote.dto.ShoppieItemDto
import com.example.shoppieclient.domain.models.ShoppieItem

fun ShoppieItemDto.toShoppieItem(): ShoppieItem {
    return ShoppieItem(
        id = id,
        name = name,
        brand = brand,
        description = description,
        images = images,
        category = category,
        price = price,
        quantity = quantity
    )
}