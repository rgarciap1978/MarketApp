package cl.rogarciap.marketapp.services

import cl.rogarciap.marketapp.dtos.CategoryDTO
import cl.rogarciap.marketapp.dtos.ProductDTO
import cl.rogarciap.marketapp.dtos.UserDTO
import cl.rogarciap.marketapp.share.request.LoginRequest
import cl.rogarciap.marketapp.share.response.GenericResponse
import cl.rogarciap.marketapp.share.response.LoginResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface IService {

    @POST("api/usuarios/login")
    suspend fun logIn(@Body request: LoginRequest): LoginResponse

    @GET("api/categorias")
    suspend fun getAllCategories(): GenericResponse<CategoryDTO>

    @GET("api/categorias/{uuid}/productos")
    suspend fun getAllProductsOfCategory(@Path("uuid") uuid: String): GenericResponse<ProductDTO>
}