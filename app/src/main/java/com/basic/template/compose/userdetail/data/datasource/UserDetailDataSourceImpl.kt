package com.basic.template.compose.userdetail.data.datasource

import com.basic.template.network.api.ApiWebService
import com.basic.template.network.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class UserDetailDataSourceImpl(private val apiWebService: ApiWebService) : UserDetailDataSource {
    override fun fetchUserDetail(userId: String): Flow<User?> = flow {
        val userDetail = apiWebService.fetchUserDetail(userId).user
        emit(userDetail)
    }.flowOn(Dispatchers.IO)
}