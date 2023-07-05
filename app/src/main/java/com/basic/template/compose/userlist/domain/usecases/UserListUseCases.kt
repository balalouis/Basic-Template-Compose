package com.basic.template.compose.userlist.domain.usecases

import com.basic.template.compose.userlist.domain.repo.UserListRepo
import com.basic.template.network.model.UserListRoot
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserListUseCases @Inject constructor(var userListRepo: UserListRepo) {
    fun fetchUserList(): Flow<UserListRoot> =
        userListRepo.fetchUserList()
}