package com.example.shoppieclient.domain.auth.models.on_boarding

import androidx.annotation.DrawableRes
import com.example.shoppieclient.R

sealed class OnBoardingPageModel(
    @DrawableRes val image: Int,
    val title: String,
    val subtitle: String
) {
    data object First: OnBoardingPageModel(
        image = R.drawable.page_1,
        title = "Start Journey with Shoppie",
        subtitle = "Smart, Gorgeous & Fashionable Collection"
    )

    data object Second: OnBoardingPageModel(
        image = R.drawable.page_2,
        title = "Follow Latest Style",
        subtitle = "There are many beautiful and attractive plants to your room"
    )

    data object Third: OnBoardingPageModel(
        image = R.drawable.page_3,
        title = "Summer Shopping 2024",
        subtitle = "Smart, Gorgeous & Fashionable Collection"
    )
}
