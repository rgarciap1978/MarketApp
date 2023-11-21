package cl.rogarciap.marketapp.models

data class UserModel(
    val uuid: String,
    val firstname: String,
    val lastname: String,
    val email: String,
    val phone: String,
    val gender: String,
    val document: String,
    val type: String
)
