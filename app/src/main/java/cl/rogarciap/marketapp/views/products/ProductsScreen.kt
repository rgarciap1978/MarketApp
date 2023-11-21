package cl.rogarciap.marketapp.views.products

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import cl.rogarciap.marketapp.models.ProductModel
import cl.rogarciap.marketapp.views.components.CardProductComponent
import cl.rogarciap.marketapp.views.components.TopBarComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductsScreen(
    uuid: String,
    viewModel: ProductsViewModel = hiltViewModel(),
    drawerState: DrawerState,
    onClick: (ProductModel) -> Unit
) {
    val productState = viewModel.productState
    val categoryState = viewModel.categoryState
    val navController = rememberNavController()

    LaunchedEffect(key1 = true) {
        viewModel.onEvent(ProductsFormEvent.loadProducts(uuid))
    }

    Scaffold(
        topBar = {
            TopBarComponent(
                navController = navController,
                drawerState = drawerState,
                title = categoryState.data?.name ?: ""
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            //.padding(it),
            contentAlignment = Alignment.TopCenter
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = it
            ) {
                productState.data?.let {
                    items(it) {
                        CardProductComponent(
                            modifier = Modifier
                                .padding(4.dp)
                                .fillMaxSize()
                                .wrapContentHeight(),
                            data = it,
                            onClick ={
                                onClick(it)
                            }
                        )
                    }
                }
            }

            if (productState.isLoading) {
                CircularProgressIndicator()
            }
        }
    }
}