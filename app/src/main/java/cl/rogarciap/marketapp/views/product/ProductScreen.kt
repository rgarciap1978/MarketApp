package cl.rogarciap.marketapp.views.product

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import cl.rogarciap.marketapp.views.products.ProductsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen(
    uuid: String,
    viewModel: ProductsViewModel = hiltViewModel(),
    drawerState: DrawerState
) {
    Box {
        Text(text = "Producto")
    }
}