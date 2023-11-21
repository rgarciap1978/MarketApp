package cl.rogarciap.marketapp.views.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cl.rogarciap.marketapp.R
import cl.rogarciap.marketapp.ui.theme.PrimaryDark
import cl.rogarciap.marketapp.ui.theme.PrimaryLight
import cl.rogarciap.marketapp.ui.theme.Purple40

@Composable
fun ImageSplashComponent(
    degrees: Float
) {
    val backgoundColor = if (isSystemInDarkTheme()) Brush.verticalGradient(listOf(PrimaryDark, Purple40))
    else  Brush.verticalGradient(listOf(PrimaryLight, Purple40))

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgoundColor),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_splash),
            contentDescription = "Logo Splash",
            modifier = Modifier
                .rotate(degrees)
                .size(180.dp)
        )
    }
}