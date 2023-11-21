package cl.rogarciap.marketapp.repositories.interfaces

import cl.rogarciap.marketapp.models.CategoryModel
import cl.rogarciap.marketapp.share.response.BaseResponse
import kotlinx.coroutines.flow.Flow

interface ICategoryRepository {

    suspend fun getAll(): BaseResponse<List<CategoryModel>>

    suspend fun sync()

    val getCategories: Flow<List<CategoryModel>>

    fun findById(uuid: String): Flow<CategoryModel>

}