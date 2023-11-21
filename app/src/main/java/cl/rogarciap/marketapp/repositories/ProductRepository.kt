package cl.rogarciap.marketapp.repositories

import cl.rogarciap.marketapp.models.CategoryModel
import cl.rogarciap.marketapp.models.ProductModel
import cl.rogarciap.marketapp.repositories.interfaces.IProductRepository
import cl.rogarciap.marketapp.services.IService
import cl.rogarciap.marketapp.services.mappers.FromListDtoToListModel
import cl.rogarciap.marketapp.share.response.BaseResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val service: IService
) : IProductRepository {
    override suspend fun getAll(uuid: String): Flow<BaseResponse<List<ProductModel>>> = flow {
        try {
            emit(BaseResponse.Loading())
            val response = service.getAllProductsOfCategory(uuid)
            if (response.success) {
                emit(BaseResponse.Successful(data = response.data.FromListDtoToListModel()))
            } else {
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

    override suspend fun findById(uuid: String): Flow<CategoryModel> {
        TODO("Not yet implemented")
    }

    override suspend fun sync(uuid: String) {
        val response = getAll(uuid = uuid)

    }
}