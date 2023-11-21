package cl.rogarciap.marketapp.views

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import cl.rogarciap.marketapp.views.home.HomeScreen
import cl.rogarciap.marketapp.views.login.LoginScreen
import cl.rogarciap.marketapp.views.splash.SplashScreen

@Composable
fun SettingNavigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = ConfigMainRoute.Splash.route
    ) {
        composable(route = ConfigMainRoute.Splash.route) {
            SplashScreen() {
                navController.popBackStack()
                navController.navigate(ConfigMainRoute.Login.route)
            }
        }

        composable(route = ConfigMainRoute.Login.route) {
            LoginScreen() {
                navController.popBackStack()
                navController.navigate(ConfigMainRoute.Home.route)
            }
        }

        composable(route = ConfigMainRoute.Home.route) {
            HomeScreen()
        }
    }
}