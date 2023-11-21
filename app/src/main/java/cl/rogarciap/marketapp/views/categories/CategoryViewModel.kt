package cl.rogarciap.marketapp.views.categories

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.rogarciap.marketapp.models.CategoryModel
import cl.rogarciap.marketapp.repositories.interfaces.ICategoryRepository
import cl.rogarciap.marketapp.views.states.GenericStateList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    val repo: ICategoryRepository
) : ViewModel() {

    var state by mutableStateOf(GenericStateList<CategoryModel>())

    init {
        onEvent(CategoryFormEvent.LoadCategory)
    }

    private fun onEvent(event: CategoryFormEvent) {
        when (event) {
            CategoryFormEvent.LoadCategory -> {
                loadCategory()
            }
        }
    }

    private fun loadCategory() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repo.sync()
            }

            repo.getCategories.catch {
                state = state.copy(isLoading = false, error = it.message)
            }.collect() {
                state = state.copy(isLoading = false, data = it)
            }
        }
    }
}