package com.basic.template.compose.login.data.datasource

import com.basic.template.network.api.ApiWebService
import com.basic.template.network.api.BaseApi
import com.basic.template.network.model.LoginRequestModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class LoginDataSourceImpl(private val apiWebService: ApiWebService) : LoginDataSource, BaseApi() {
    override fun fetchLoginApi(loginRequestModel: LoginRequestModel) = flow {
        val loginResponseModel = safeApiCall { apiWebService.loginApiCall(loginRequestModel) }
        emit(loginResponseModel)
    }.flowOn(Dispatchers.IO)
}
