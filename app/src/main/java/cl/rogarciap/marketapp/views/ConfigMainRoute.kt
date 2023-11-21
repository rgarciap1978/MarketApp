package cl.rogarciap.marketapp.views

sealed class ConfigMainRoute(
    val route: String
) {
    object Splash : ConfigMainRoute("splash_screen")

    object Login: ConfigMainRoute("login_screen")

    object Home : ConfigMainRoute("home_screen")
}