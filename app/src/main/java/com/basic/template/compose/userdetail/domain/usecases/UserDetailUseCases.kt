package com.basic.template.compose.userdetail.domain.usecases

import com.basic.template.compose.userdetail.domain.repo.UserDetailRepo
import com.basic.template.network.model.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class UserDetailUseCases @Inject constructor(var userDetailRepo: UserDetailRepo) {
    fun fetchUserList(userId: String): Flow<User?> = userDetailRepo.fetchUserDetail(userId)
}