package cl.rogarciap.marketapp.views.categories

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import cl.rogarciap.marketapp.models.CategoryModel
import cl.rogarciap.marketapp.views.components.CardCategoryComponent
import cl.rogarciap.marketapp.views.components.TopBarComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryScreen(
    viewModel: CategoryViewModel = hiltViewModel(),
    drawerState: DrawerState,
    onClick: (CategoryModel) -> Unit
) {
    val state = viewModel.state

    val navController= rememberNavController()

    Scaffold(
        topBar = {
            TopBarComponent(
                navController = navController,
                drawerState = drawerState,
                title = "Bienvenido"
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentAlignment = Alignment.Center
        ) {
            LazyColumn(
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                state.data?.let {
                    items(it) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Green)
                        ) {
                            CardCategoryComponent(
                                data = it,
                                onClick = {
                                    onClick(it)
                                }
                            )
                        }
                    }
                }
            }

            if(state.isLoading){
                CircularProgressIndicator()
            }
        }
    }
}