package com.basic.template.network.userlist

import com.basic.template.network.model.NetworkResponse
import com.basic.template.network.model.UserListRoot
import kotlinx.coroutines.flow.Flow

interface UserListDataSource {
    fun fetchUserList(): Flow<NetworkResponse<UserListRoot>>
}