package cl.rogarciap.marketapp.repositories.interfaces

import cl.rogarciap.marketapp.models.CategoryModel
import cl.rogarciap.marketapp.models.ProductModel
import cl.rogarciap.marketapp.share.response.BaseResponse
import kotlinx.coroutines.flow.Flow

interface IProductRepository {
    suspend fun getAll(uuid:String):Flow<BaseResponse<List<ProductModel>>>

    suspend fun findById(uuid: String): Flow<CategoryModel>

    suspend fun sync(uuid: String)

}