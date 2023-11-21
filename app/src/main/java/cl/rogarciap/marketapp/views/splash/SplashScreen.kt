package cl.rogarciap.marketapp.views.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import cl.rogarciap.marketapp.views.components.ImageSplashComponent

@Composable
fun SplashScreen(
    onFinished: () -> Unit
) {
    val degrees= remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = true){
        degrees.animateTo(
            targetValue = 360f,
            animationSpec = tween(
                durationMillis = 3000,
                delayMillis = 1000
            )
        )

        onFinished()
    }

    ImageSplashComponent(degrees = degrees.value)
}

@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen(){}
}