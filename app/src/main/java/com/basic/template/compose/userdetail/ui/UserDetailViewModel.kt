package com.basic.template.compose.userdetail.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.basic.template.compose.userdetail.domain.usecases.UserDetailUseCases
import com.basic.template.network.model.NetworkResponse
import com.basic.template.network.model.UserDetailServerRootData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import model.RoomUser
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(private var userDetailUseCases: UserDetailUseCases) :
    ViewModel() {
    private val _uiState =
        MutableStateFlow<NetworkResponse<RoomUser>>(NetworkResponse.Success(data = RoomUser()))
    val uiState: StateFlow<NetworkResponse<RoomUser>> = _uiState

    private val _uiStateNetwork =
        MutableStateFlow<NetworkResponse<UserDetailServerRootData>>(NetworkResponse.Success(data = UserDetailServerRootData()))
    val uiStateNetwork: StateFlow<NetworkResponse<UserDetailServerRootData>> = _uiStateNetwork

    fun fetchUserAndInsertIntoDB(userId: String) {
        viewModelScope.launch {
            userDetailUseCases.fetchUserAndInsertIntoDB(userId).catch {
                _uiStateNetwork.value = NetworkResponse.Failure(it.localizedMessage)
            }.collect {
                _uiStateNetwork.value = it
            }
        }
    }

    fun fetchRoomUserFromDBViaViewModel(userId: Int) {
        viewModelScope.launch {
            userDetailUseCases.fetchUserDetailFromDB(userId)
                .catch {
                    _uiState.value = NetworkResponse.Failure(it.localizedMessage)
                }
                .collect {
                    _uiState.value = NetworkResponse.Success(it)
                }
        }
    }
}