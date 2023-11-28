package com.basic.template.compose.registeration.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.basic.template.compose.registeration.domain.usecases.RegistrationUseCases
import com.basic.template.network.model.RegistrationRequestModel
import com.basic.template.network.model.RegistrationResponseModel
import com.basic.template.network.model.RegistrationUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(private var registrationUseCases: RegistrationUseCases) : ViewModel() {

    private val _uiState = MutableStateFlow<RegistrationUiState>(
        RegistrationUiState.Success(
            RegistrationResponseModel()
        )
    )
    val uiState: StateFlow<RegistrationUiState> = _uiState

    fun registrationViaApiViewModel(registrationRequestModel: RegistrationRequestModel) {
        viewModelScope.launch {
            registrationUseCases.registration(registrationRequestModel)
                .catch {
                    _uiState.value = RegistrationUiState.Error(it)
                }
                .collect {
                    _uiState.value = RegistrationUiState.Success(it)
                }
        }
    }

}