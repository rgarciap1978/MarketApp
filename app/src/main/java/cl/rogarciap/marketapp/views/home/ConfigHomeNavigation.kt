package cl.rogarciap.marketapp.views.home

import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import cl.rogarciap.marketapp.views.categories.CategoryScreen
import cl.rogarciap.marketapp.views.history.HistoryScreen
import cl.rogarciap.marketapp.views.orders.OrdersScreen
import cl.rogarciap.marketapp.views.product.ProductScreen
import cl.rogarciap.marketapp.views.products.ProductsScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfigHomeNavigation(
    navController: NavHostController,
    drawerState: DrawerState
) {
    NavHost(
        navController = navController,
        startDestination = ConfigHomeRoute.Category.route
    ) {
        composable(route = ConfigHomeRoute.Category.route) {
            CategoryScreen(drawerState = drawerState) {
                navController
                    .navigate(ConfigHomeRoute.Products.createRoute(it.uuid))
            }
        }

        composable(route = ConfigHomeRoute.Orders.route) {
            OrdersScreen()
        }

        composable(route = ConfigHomeRoute.History.route) {
            HistoryScreen()
        }

        composable(
            route = ConfigHomeRoute.Products.route,
            arguments = listOf(navArgument("uuid") {
                defaultValue = ""
            })
        ) {
            val uuid = it.arguments?.getString("uuid")
            uuid?.let {
                ProductsScreen(
                    uuid = it,
                    drawerState = drawerState
                ) {
                    navController
                        .navigate(ConfigHomeRoute.Product.createRoute(it.uuid))
                }
            }
        }

        composable(
            route = ConfigHomeRoute.Product.route,
            arguments = listOf(navArgument("uuid") {
                defaultValue = ""
            })
        ) {
            val uuid=it.arguments?.getString("uuid")
            uuid?.let {
                ProductScreen(
                    uuid=it,
                    drawerState=drawerState
                )
            }
        }

    }
}