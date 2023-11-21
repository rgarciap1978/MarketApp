package cl.rogarciap.marketapp.views.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.rogarciap.marketapp.repositories.interfaces.IUserRepository
import cl.rogarciap.marketapp.share.request.LoginRequest
import cl.rogarciap.marketapp.share.response.BaseResponse
import cl.rogarciap.marketapp.views.states.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    val repo: IUserRepository
) : ViewModel() {

    var state by mutableStateOf(LoginState())
    var stateElement by mutableStateOf(LoginElement())

    fun onEvent(event: LoginFormEvent) {
        when (event) {
            LoginFormEvent.Submit -> {
                logIn()
            }

            is LoginFormEvent.VisualTransformationChange -> {
                stateElement = stateElement.copy(visualTransformation = event.visualTransformation)
            }

            is LoginFormEvent.EmailChange -> {
                stateElement = stateElement.copy(email = event.email)
            }

            is LoginFormEvent.PasswordChange -> {
                stateElement = stateElement.copy(password = event.password)
            }
        }
    }

    private fun logIn() {
        viewModelScope.launch {
            repo
                .logIn(
                    LoginRequest(
                        stateElement.email,
                        stateElement.password
                    )
                )
                .onEach {
                    when (it) {
                        is BaseResponse.Error -> {
                            state = state.copy(
                                error = it.message,
                                isLoading = false,
                                data = null
                            )
                        }

                        is BaseResponse.Loading -> {
                            state = state.copy(isLoading = true)
                        }

                        is BaseResponse.Successful -> {
                            state = state.copy(
                                data = it.data,
                                isLoading = false,
                                error = null
                            )
                        }
                    }
                }.launchIn(viewModelScope)
        }
    }

    fun resetStateError() {
        state = state.copy(
            data = null,
            error = null
        )
    }
}