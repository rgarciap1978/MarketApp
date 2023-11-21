package cl.rogarciap.marketapp.views.states

import cl.rogarciap.marketapp.models.UserModel

data class LoginState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val data: UserModel? = null,

)
