package com.basic.template.compose.userlist.data.datasource

import com.basic.template.network.api.ApiWebService
import com.basic.template.network.api.BaseApi
import com.basic.template.network.model.NetworkResponse
import com.basic.template.network.model.UserListRoot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class UserListDataSourceImpl(private val apiWebService: ApiWebService) : UserListDataSource,
    BaseApi() {

    override fun fetchUserList(): Flow<NetworkResponse<UserListRoot>> = flow {
        val loginResponseModel = safeApiCall { apiWebService.fetchUserList() }
        emit(loginResponseModel)
    }.flowOn(Dispatchers.IO)
}
