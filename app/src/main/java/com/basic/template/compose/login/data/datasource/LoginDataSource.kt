package com.basic.template.compose.login.data.datasource

import com.basic.template.network.model.LoginRequestModel
import com.basic.template.network.model.LoginResponseModel
import kotlinx.coroutines.flow.Flow

interface LoginDataSource {
    fun fetchLoginApi(loginRequestModel: LoginRequestModel): Flow<LoginResponseModel>
}