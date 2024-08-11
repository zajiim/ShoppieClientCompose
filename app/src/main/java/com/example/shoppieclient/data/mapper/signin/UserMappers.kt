package com.example.shoppieclient.data.mapper.signin


import android.util.Log
import com.example.shoppieclient.data.remote.dto.CartCountDto
import com.example.shoppieclient.data.remote.dto.CartItemDto
import com.example.shoppieclient.data.remote.dto.ProductDto
import com.example.shoppieclient.data.remote.dto.UserDto
import com.example.shoppieclient.domain.auth.models.home.CartCount
import com.example.shoppieclient.domain.auth.models.signin.CartItem
import com.example.shoppieclient.domain.auth.models.signin.Product
import com.example.shoppieclient.domain.auth.models.signin.User

fun UserDto.toUser(): User {
    return User(
        token = this.token,
        id = this._id,
        name = this.name,
        email = this.email,
        address = this.address,
        type = this.type,
        cart = this.cart.map { cartItemDto ->
                cartItemDto.toCartItem()
        }
    )
}

fun CartItemDto.toCartItem(): CartItem {
    return CartItem(
        product = this.product.toProduct(),
        quantity = this.quantity
    )
}

fun ProductDto.toProduct(): Product {
    return Product(
        productId = this.productId,
        name = this.name,
        brand = this.brand,
        description = this.description,
        quantity = this.quantity,
        price = this.price,
        category = this.category,
        images = this.images
    )
}

fun CartCountDto.toCartCount(): CartCount {
    return CartCount(
        status = this.status,
        cartCount = this.cartCount
    )
}