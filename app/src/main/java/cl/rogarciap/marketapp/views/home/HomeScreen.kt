package cl.rogarciap.marketapp.views.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import cl.rogarciap.marketapp.views.components.DrawerContentComponent
import cl.rogarciap.marketapp.views.DrawerItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val items = arrayOf(
        DrawerItem(
            icon = Icons.Filled.Home,
            title = "Inicio",
            route = ConfigHomeRoute.Category.route
        ),
        DrawerItem(
            icon = Icons.Filled.ShoppingCart,
            title = "Mis comprar",
            route = ConfigHomeRoute.Orders.route
        ),
        DrawerItem(
            icon = Icons.Filled.History,
            title = "Mi historial",
            route = ConfigHomeRoute.History.route
        )
    )

    val navController: NavHostController = rememberNavController()
    val coroutineScope: CoroutineScope = rememberCoroutineScope()
    val drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                DrawerContentComponent(
                    items = items
                ) { route ->
                    coroutineScope.launch {
                        drawerState.close()
                    }

                    navController.navigate(route)
                }
            }
        }
    ) {
        ConfigHomeNavigation(
            navController = navController,
            drawerState = drawerState
        )
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}