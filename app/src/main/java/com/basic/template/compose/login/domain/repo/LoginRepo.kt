package com.basic.template.compose.login.domain.repo

import com.basic.template.network.model.LoginRequestModel
import com.basic.template.network.model.LoginResponseModel
import com.basic.template.network.model.NetworkResponse
import kotlinx.coroutines.flow.Flow

interface LoginRepo {
    fun login(loginRequestModel: LoginRequestModel): Flow<NetworkResponse<LoginResponseModel>>
}