package com.basic.template.compose.userdetail.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.basic.template.compose.userdetail.domain.usecases.UserDetailUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import model.RoomUser
import model.RoomUserDetailUIState
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(private var userDetailUseCases: UserDetailUseCases) :
    ViewModel() {
    private val _uiState =
        MutableStateFlow<RoomUserDetailUIState>(RoomUserDetailUIState.Success(user = RoomUser()))
    val uiState: StateFlow<RoomUserDetailUIState> = _uiState

    fun fetchUserAndInsertIntoDB(userId: String) {
        viewModelScope.launch {
            userDetailUseCases.fetchUserAndInsertIntoDB(userId).catch {
                _uiState.value = RoomUserDetailUIState.Failure(it)
            }.collect {
                Log.d("---->", "Successfully fetched the data")
            }
        }
    }

    fun fetchRoomUserFromDBViaViewModel(userId: Int) {
        viewModelScope.launch {
            userDetailUseCases.fetchUserDetailFromDB(userId)
                .catch {
                    _uiState.value = RoomUserDetailUIState.Failure(it)
                }
                .collect {
                    _uiState.value = RoomUserDetailUIState.Success(it)
                }
        }
    }
}