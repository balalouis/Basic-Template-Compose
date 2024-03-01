package com.example.userlist

import com.basic.template.network.model.NetworkResponse
import com.basic.template.network.model.UserListRoot
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserListUseCases @Inject constructor(var userListRepo: UserListRepo) {
    fun fetchUserList(): Flow<NetworkResponse<UserListRoot>> =
        userListRepo.fetchUserList()
}