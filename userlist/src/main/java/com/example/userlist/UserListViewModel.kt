package com.example.userlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.basic.template.network.model.NetworkResponse
import com.basic.template.network.model.UserListRoot
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(var userListUseCases: UserListUseCases) : ViewModel() {
    private val _uiState =
        MutableStateFlow<NetworkResponse<UserListRoot>>(NetworkResponse.Success(null))
    val uiState: StateFlow<NetworkResponse<UserListRoot>> = _uiState

    fun fetchUserListApiViaViewModel() {
        viewModelScope.launch {
            _uiState.value = NetworkResponse.Loading
            userListUseCases.fetchUserList()
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
