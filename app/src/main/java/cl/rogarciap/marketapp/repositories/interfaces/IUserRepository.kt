package cl.rogarciap.marketapp.repositories.interfaces

import cl.rogarciap.marketapp.models.UserModel
import cl.rogarciap.marketapp.share.request.LoginRequest
import cl.rogarciap.marketapp.share.response.BaseResponse
import kotlinx.coroutines.flow.Flow

interface IUserRepository {

    suspend fun logIn(request: LoginRequest): Flow<BaseResponse<UserModel>>

}