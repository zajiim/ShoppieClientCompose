package com.example.shoppieclient.data.mapper.cart

import com.example.shoppieclient.data.remote.dto.cart.CartDetailDto
import com.example.shoppieclient.data.remote.dto.cart.CartDto
import com.example.shoppieclient.data.remote.dto.cart.ProductDto
import com.example.shoppieclient.domain.auth.models.cart.Cart
import com.example.shoppieclient.domain.auth.models.cart.CartDetails
import com.example.shoppieclient.domain.auth.models.cart.Products

fun CartDto.toCart(): Cart {
    return Cart(
        cartDetails = this.cartDetails.map { it.toCartDetail() },
        currentPage = currentPage,
        status = status,
        totalCartItems = totalCartItems,
        totalPages = totalPages
    )
}

fun CartDetailDto.toCartDetail(): CartDetails {
    return CartDetails(
        id = _id,
        product = product.toProducts(),
        quantity = quantity
    )
}

fun ProductDto.toProducts(): Products {
    return Products(
        brand = brand,
        category = category,
        description = description,
        id = _id,
        images = images,
        name = name,
        price = price,
        productId = productId,
        quantity = quantity,
        v = v
    )
}