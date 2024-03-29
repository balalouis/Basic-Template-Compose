package com.login

import com.basic.template.network.login.LoginDataSource
import com.basic.template.network.model.LoginRequestModel
import com.basic.template.network.model.LoginResponseModel
import com.basic.template.network.model.NetworkResponse
import kotlinx.coroutines.flow.Flow

class LoginRepoImpl(private var loginDataSource: LoginDataSource):LoginRepo {
    override fun login(loginRequestModel: LoginRequestModel): Flow<NetworkResponse<LoginResponseModel>> =
        loginDataSource.fetchLoginApi(loginRequestModel)
}