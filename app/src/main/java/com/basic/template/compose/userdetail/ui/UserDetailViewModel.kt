package com.basic.template.compose.userdetail.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.basic.template.compose.userdetail.domain.usecases.UserDetailUseCases
import com.basic.template.network.model.NetworkResponse
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

    fun fetchUserAndInsertIntoDB(userId: String) {
        viewModelScope.launch {
            userDetailUseCases.fetchUserAndInsertIntoDB(userId).catch {
                _uiState.value = NetworkResponse.Failure(it.localizedMessage)
            }.collect {
                Log.d("---->", "Successfully fetched the data")
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