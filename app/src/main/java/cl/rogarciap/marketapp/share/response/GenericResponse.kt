package cl.rogarciap.marketapp.share.response

data class GenericResponse<T>(
    val success: Boolean,
    val message: String,
    val data: List<T>
)