package com.basic.template.compose.userdetail.data.repo

import com.basic.template.compose.userdetail.data.datasource.UserDetailDataSource
import com.basic.template.compose.userdetail.domain.repo.UserDetailRepo
import com.basic.template.network.model.User
import kotlinx.coroutines.flow.Flow

class UserDetailRepoImpl(var userDetailDataSource: UserDetailDataSource) : UserDetailRepo {
    override fun fetchUserDetail(userId:String): Flow<User?> = userDetailDataSource.fetchUserDetail(userId)
}