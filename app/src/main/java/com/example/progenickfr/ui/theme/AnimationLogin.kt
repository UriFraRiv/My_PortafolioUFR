package com.example.progenickfr.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.progenickfr.R
import kotlinx.coroutines.delay

@Composable

fun AnimationLogin (navigateToNext: () -> Unit){

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.powerbot))
    LaunchedEffect(Unit) {
        delay(2500)
        navigateToNext()
    }
    Box(
        modifier = Modifier.Companion
            .fillMaxSize()
            .background(Color.Companion.Black),
        contentAlignment = Alignment.Companion.Center
    ) {
        LottieAnimation(composition = composition)
    }
}