package com.example.userlist

import com.basic.template.network.model.NetworkResponse
import com.basic.template.network.model.UserListRoot
import com.basic.template.network.userlist.UserListDataSource
import kotlinx.coroutines.flow.Flow

class UserListRepoImpl(var userListDataSource: UserListDataSource) : UserListRepo {
    override fun fetchUserList(): Flow<NetworkResponse<UserListRoot>> =
        userListDataSource.fetchUserList()
}