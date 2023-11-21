package cl.rogarciap.marketapp.views.home

sealed class ConfigHomeRoute(val route: String) {

    object Category : ConfigHomeRoute("category_screen")

    object Orders : ConfigHomeRoute("orders_screen")

    object History : ConfigHomeRoute("history_screen")

    object Products : ConfigHomeRoute("products_screen/?uuid={uuid}") {
        fun createRoute(uuid: String) = "products_screen/?uuid=$uuid"
    }

    object Product : ConfigHomeRoute("product_screen/?uuid={uuid}") {
        fun createRoute(uuid: String) = "product_screen/?uuid=$uuid"
    }
}
