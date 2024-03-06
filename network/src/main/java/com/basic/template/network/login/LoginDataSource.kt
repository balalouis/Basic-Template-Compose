package com.basic.template.network.login

import com.basic.template.network.model.LoginRequestModel
import com.basic.template.network.model.LoginResponseModel
import com.basic.template.network.model.NetworkResponse
import kotlinx.coroutines.flow.Flow

interface LoginDataSource {
    fun fetchLoginApi(loginRequestModel: LoginRequestModel): Flow<NetworkResponse<LoginResponseModel>>
}