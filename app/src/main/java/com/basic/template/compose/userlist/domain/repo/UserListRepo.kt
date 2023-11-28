package com.basic.template.compose.userlist.domain.repo

import com.basic.template.network.model.NetworkResponse
import com.basic.template.network.model.UserListRoot
import kotlinx.coroutines.flow.Flow

interface UserListRepo {
    fun fetchUserList(): Flow<NetworkResponse<UserListRoot>>
}