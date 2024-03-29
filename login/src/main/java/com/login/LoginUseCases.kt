package com.login

import com.basic.template.network.model.LoginRequestModel
import com.basic.template.network.model.LoginResponseModel
import com.basic.template.network.model.NetworkResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCases @Inject constructor(private var loginRepo: LoginRepo) {
    fun login(loginRequestModel: LoginRequestModel): Flow<NetworkResponse<LoginResponseModel>> =
        loginRepo.login(loginRequestModel)
}