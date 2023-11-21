package cl.rogarciap.marketapp.views.states

data class GenericState<T> (
    val isLoading: Boolean = false,
    val error: String? = null,
    val data: T? = null
)