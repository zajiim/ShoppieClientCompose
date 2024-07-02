package com.example.shoppieclient.presentation.on_boarding

sealed class OnBoardingEvent {
    data object saveOnBoardingData: OnBoardingEvent()
}