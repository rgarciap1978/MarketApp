package cl.rogarciap.marketapp.views.products

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.rogarciap.marketapp.models.CategoryModel
import cl.rogarciap.marketapp.models.ProductModel
import cl.rogarciap.marketapp.repositories.interfaces.ICategoryRepository
import cl.rogarciap.marketapp.repositories.interfaces.IProductRepository
import cl.rogarciap.marketapp.share.response.BaseResponse
import cl.rogarciap.marketapp.views.states.GenericState
import cl.rogarciap.marketapp.views.states.GenericStateList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val productRepository: IProductRepository,
    private val categoryRepository: ICategoryRepository
) : ViewModel() {

    var productState by mutableStateOf(GenericStateList<ProductModel>())
    var categoryState by mutableStateOf(GenericState<CategoryModel>())

    fun onEvent(event: ProductsFormEvent) {
        when (event) {
            is ProductsFormEvent.loadProducts -> {
                getProducts(event.uuid)
                getCategory(event.uuid)
            }
        }
    }

    private fun getCategory(uuid: String) {
        viewModelScope.launch {
            try {
                val category = withContext(Dispatchers.IO) {
                    categoryRepository.findById(uuid).first()
                }

                categoryState = categoryState.copy(
                    data = category
                )
            } catch (ex: Exception) {
                println(ex.message)
            }
        }
    }

    private fun getProducts(uuid: String) {
        viewModelScope.launch {
            productRepository.getAll(uuid).onEach {
                when (it) {
                    is BaseResponse.Error -> {
                        productState = productState.copy(
                            isLoading = false,
                            error = it.message
                        )
                    }

                    is BaseResponse.Loading -> {
                        productState = productState.copy(
                            isLoading = true
                        )
                    }

                    is BaseResponse.Successful -> {
                        productState = productState.copy(
                            isLoading = false,
                            data = it.data
                        )
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}