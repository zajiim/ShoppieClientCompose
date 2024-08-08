package com.example.shoppieclient.data.mapper.signin


import com.example.shoppieclient.data.remote.dto.CartItemDto
import com.example.shoppieclient.data.remote.dto.ProductDto
import com.example.shoppieclient.data.remote.dto.UserDto
import com.example.shoppieclient.domain.auth.models.signin.CartItem
import com.example.shoppieclient.domain.auth.models.signin.Product
import com.example.shoppieclient.domain.auth.models.signin.User

fun UserDto.toUser(): User {
    return User(
        token = token,
        id = id,
        name = name,
        email = email,
        address = address,
        type = type,
        cart = cart.map { it.toCartItem() }
    )
}

fun CartItemDto.toCartItem(): CartItem {
    return CartItem(
        product = product.toProductItem(),
        quantity = quantity
    )
}


fun ProductDto.toProductItem(): Product {
    return Product(
        productId = productId,
        name = name,
        brand = brand,
        description = description,
        quantity = quantity,
        price = price,
        category = category,
        images = images
    )
}