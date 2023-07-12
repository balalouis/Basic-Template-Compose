package com.basic.template.compose.userlist.data.repo

import com.basic.template.compose.userlist.data.datasource.UserListDataSource
import com.basic.template.compose.userlist.domain.repo.UserListRepo
import com.basic.template.network.model.UserListRoot
import kotlinx.coroutines.flow.Flow

class UserListRepoImpl(var userListDataSource: UserListDataSource) : UserListRepo {
    override fun fetchUserList(): Flow<UserListRoot> = userListDataSource.fetchUserList()
}