package cl.rogarciap.marketapp.views.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RoundedButtonComponent(
    modifier: Modifier = Modifier,
    title: String,
    onClick: () -> Unit,
    displayProgressBar: Boolean = false
) {
    if (!displayProgressBar) {
        Button(
            onClick = { onClick() },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black
            ),
            modifier = modifier,
            border = BorderStroke(width = 1.dp, color = Color.Black),
            shape = RoundedCornerShape(50)
        ) {
            Text(
                text = title,
                fontSize = 16.sp,
                color = Color.White
            )
        }
    } else {
        CircularProgressIndicator(
            modifier = Modifier.size(50.dp),
            strokeWidth = 4.dp
        )
    }
}