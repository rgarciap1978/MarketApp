package cl.rogarciap.marketapp.views.states

data class GenericStateList<T>(
    val isLoading: Boolean = false,
    val error: String? = null,
    val data: List<T>? = null
)
