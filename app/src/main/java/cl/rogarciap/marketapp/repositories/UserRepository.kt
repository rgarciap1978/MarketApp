package cl.rogarciap.marketapp.repositories

import cl.rogarciap.marketapp.models.UserModel
import cl.rogarciap.marketapp.repositories.interfaces.IUserRepository
import cl.rogarciap.marketapp.services.IService
import cl.rogarciap.marketapp.services.mappers.toModel
import cl.rogarciap.marketapp.services.TokenManager
import cl.rogarciap.marketapp.share.request.LoginRequest
import cl.rogarciap.marketapp.share.response.BaseResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UserRepository @Inject constructor(
    val service: IService
) : IUserRepository{
    override suspend fun logIn(request: LoginRequest): Flow<BaseResponse<UserModel>> = flow {
        try {
            emit(BaseResponse.Loading())

            val response = service.logIn(request)
            if(response.success){
                TokenManager.setToken(response.token)
                emit(BaseResponse.Successful(data = response.data?.toModel()))
            }else{
                emit(BaseResponse.Error(message = response.message))
            }
        } catch (ex: HttpException) {
            emit(BaseResponse.Error(message = "Encontramos un error en tu solicitud"))
        } catch (ex: IOException) {
            emit(BaseResponse.Error(message = "No se pudo conectar al servidor"))
        } catch (ex: Exception) {
            emit(BaseResponse.Error(message = ex.message.toString()))
        }
    }
}