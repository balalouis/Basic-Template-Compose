package com.basic.template.compose.login.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.basic.template.compose.login.domain.usecases.LoginUseCases
import com.basic.template.network.model.LoginRequestModel
import com.basic.template.network.model.LoginResponseModel
import com.basic.template.network.model.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private var loginUseCases: LoginUseCases) : ViewModel() {

    private val _uiState =
        MutableStateFlow<NetworkResponse<LoginResponseModel>>(NetworkResponse.Success(data = null))
    val uiState: StateFlow<NetworkResponse<LoginResponseModel>> = _uiState

    private val defaultLoginRequestModel =
        LoginRequestModel(email = "", password = "")

    fun loginApiViewModel(loginRequestModel: LoginRequestModel = defaultLoginRequestModel) {
        viewModelScope.launch {
            _uiState.value = NetworkResponse.Loading
            loginUseCases.login(loginRequestModel)
                .catch {
                    _uiState.value =
                        it.localizedMessage?.let { it1 -> NetworkResponse.Failure(it1) }!!
                }
                .collect {
                    _uiState.value = it
                }
        }
    }
}