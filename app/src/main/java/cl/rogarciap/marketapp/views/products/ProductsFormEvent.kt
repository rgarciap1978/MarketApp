package cl.rogarciap.marketapp.views.products

sealed class ProductsFormEvent {
    data class loadProducts(val uuid: String) : ProductsFormEvent()
}
