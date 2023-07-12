package com.basic.template.compose.userdetail.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.basic.template.compose.userdetail.domain.usecases.UserDetailUseCases
import com.basic.template.network.model.User
import com.basic.template.network.model.UserDetailUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(var userDetailUseCases: UserDetailUseCases) : ViewModel() {
    private val _uiState = MutableStateFlow<UserDetailUIState>(UserDetailUIState.Success(user = User()))
    val uiState: StateFlow<UserDetailUIState> = _uiState

    fun fetchUserDetailViaViewModel(userId:String) {
        viewModelScope.launch {
            userDetailUseCases.fetchUserList(userId)
                .catch {
                    _uiState.value = UserDetailUIState.Failure(it)
                }
                .collect {
                    _uiState.value = UserDetailUIState.Success(it)
                }
        }
    }
}