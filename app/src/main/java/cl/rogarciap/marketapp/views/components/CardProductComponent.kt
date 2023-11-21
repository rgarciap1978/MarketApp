package cl.rogarciap.marketapp.views.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import cl.rogarciap.marketapp.models.ProductModel
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun CardProductComponent(
    modifier: Modifier = Modifier,
    data: ProductModel,
    onClick: (ProductModel) -> Unit
) {
    Card(
        modifier = modifier
            .clickable {
                onClick(data)
            },
        border = BorderStroke(1.dp, Color.Black),
        elevation = CardDefaults.cardElevation(defaultElevation = 16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Column {
                AsyncImage(
                    model = ImageRequest
                        .Builder(LocalContext.current)
                        .data(data.images[0])
                        .crossfade(2000)
                        .build(),
                    contentDescription = data.description
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Cod: ${data.code}")
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = data.description)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "$" + data.price)
            }
        }
    }
}