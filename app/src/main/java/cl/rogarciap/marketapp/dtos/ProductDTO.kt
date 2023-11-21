package cl.rogarciap.marketapp.dtos

import com.google.gson.annotations.SerializedName

data class ProductDTO(
    val uuid: String,

    @SerializedName("descripcion")
    val description: String,

    @SerializedName("codigo")
    val code: String,

    @SerializedName("caracteristicas")
    val features: String,

    @SerializedName("precio")
    val price: Double,

    val stock: Int,

    @SerializedName("imagenes")
    val images: List<String>
)
