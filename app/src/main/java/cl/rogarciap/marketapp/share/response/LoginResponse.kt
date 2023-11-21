package cl.rogarciap.marketapp.share.response

import cl.rogarciap.marketapp.dtos.UserDTO

data class LoginResponse(
    val success: Boolean,
    val message: String,
    val data: UserDTO,
    val token: String
)
