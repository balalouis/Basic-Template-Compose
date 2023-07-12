package com.basic.template.compose.userlist.data.datasource

import com.basic.template.network.model.UserListRoot
import kotlinx.coroutines.flow.Flow

interface UserListDataSource {
    fun fetchUserList(): Flow<UserListRoot>
}