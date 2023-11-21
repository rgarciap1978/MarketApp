package cl.rogarciap.marketapp.models

data class ProductModel(
    val uuid: String,
    val description: String,
    val code: String,
    val features: String,
    val price: Double,
    val stock: Int,
    val images: List<String>
)
