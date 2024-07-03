package com.example.shoppieclient.presentation.on_boarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.shoppieclient.core.navigation.Graph
import com.example.shoppieclient.domain.auth.models.on_boarding.OnBoardingPageModel
import com.example.shoppieclient.presentation.on_boarding.components.CustomButtonOnBoarding
import com.example.shoppieclient.presentation.on_boarding.components.CustomPagerIndicator
import com.example.shoppieclient.presentation.on_boarding.components.CustomTextButton
import com.example.shoppieclient.presentation.on_boarding.components.PagerScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(
    navController: NavHostController,
    event: (OnBoardingEvent) -> Unit
) {
    val pages = listOf(
        OnBoardingPageModel.First, OnBoardingPageModel.Second, OnBoardingPageModel.Third
    )

    val pagerState = rememberPagerState(initialPage = 0, pageCount = { pages.size })

    val buttonState = remember {
        derivedStateOf {
            when(pagerState.currentPage) {
                0 -> listOf("", "Next")
                1 -> listOf("Back", "Next")
                2 -> listOf("Back", "Get Started")
                else -> listOf("", "")
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f)
        ) { index ->

            PagerScreen(
                onBoardingPager = pages[index],
                modifier = Modifier
            )
        }

        Row(
            modifier = Modifier.fillMaxSize().padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            CustomPagerIndicator(
                pageCount = pages.size,
                currentPage = pagerState.currentPage,
                modifier = Modifier
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                val scope = rememberCoroutineScope()
                if (buttonState.value[0].isNotEmpty()) {
                    CustomTextButton(text = buttonState.value[0],
                        onClick =  {
                            scope.launch {
                                pagerState.animateScrollToPage(page = pagerState.currentPage - 1)
                            }
                        })
                }
                
                CustomButtonOnBoarding(text = buttonState.value[1],
                    onClick = {
                        scope.launch {
                            if(pagerState.currentPage == 2) {
                                event(OnBoardingEvent.saveOnBoardingData)
                                navController.navigate(route = Graph.AUTH)
                            } else {
                                pagerState.animateScrollToPage(
                                    page = pagerState.currentPage + 1
                                )
                            }
                        }
                    }
                    )

            }

        }

    }

}

