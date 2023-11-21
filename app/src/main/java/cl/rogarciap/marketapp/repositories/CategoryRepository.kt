package cl.rogarciap.marketapp.repositories

import cl.rogarciap.marketapp.daos.ICategoryDAO
import cl.rogarciap.marketapp.models.CategoryModel
import cl.rogarciap.marketapp.repositories.interfaces.ICategoryRepository
import cl.rogarciap.marketapp.services.IService
import cl.rogarciap.marketapp.services.mappers.FromListDtoToListModel
import cl.rogarciap.marketapp.services.mappers.FromListEntityToListModel
import cl.rogarciap.marketapp.services.mappers.FromListModelToListEntity
import cl.rogarciap.marketapp.services.mappers.toModel
import cl.rogarciap.marketapp.share.response.BaseResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    private val service: IService, private val dao: ICategoryDAO
) : ICategoryRepository {

    override suspend fun getAll(): BaseResponse<List<CategoryModel>> {
        return try {
            val response = service.getAllCategories()
            if (response.success) {
                BaseResponse.Successful(data = response.data.FromListDtoToListModel())
            } else {
                BaseResponse.Error(message = response.message)
            }
        } catch (ex: HttpException) {
            BaseResponse.Error(message = "Encontramos un error en tu solicitud:\n${ex.message}")
        } catch (ex: IOException) {
            BaseResponse.Error(message = "No se pudo conectar al servidor:\n${ex.message}")
        } catch (ex: Exception) {
            BaseResponse.Error(message = ex.message.toString())
        }
    }

    override suspend fun sync() {
        val response = getAll()
        response.data?.let {
            if (it.size > dao.count()) {
                dao.insert(it.FromListModelToListEntity())
            }
        }
    }

    override val getCategories: Flow<List<CategoryModel>> = dao.select().map {
        it.FromListEntityToListModel()
    }

    override fun findById(uuid: String): Flow<CategoryModel> = flow {
        try {
            val category = dao.selectWhereId(uuid)
            emit(category.toModel())
        }catch (ex: Exception){
            println(ex.message)
        }
    }

}