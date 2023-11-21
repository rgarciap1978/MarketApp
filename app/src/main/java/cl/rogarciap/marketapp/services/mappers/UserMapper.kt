package cl.rogarciap.marketapp.services.mappers

import cl.rogarciap.marketapp.dtos.UserDTO
import cl.rogarciap.marketapp.models.UserModel

fun UserDTO.toModel(): UserModel {
    return UserModel(
        uuid = uuid,
        firstname = Firstname,
        lastname = Lastname,
        email = Email,
        phone = Phone,
        gender = Gender,
        document = Id,
        type = Type
    )
}