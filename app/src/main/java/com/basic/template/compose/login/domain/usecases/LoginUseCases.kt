package com.basic.template.compose.login.domain.usecases

import com.basic.template.compose.login.domain.repo.LoginRepo
import com.basic.template.network.model.LoginRequestModel
import com.basic.template.network.model.LoginResponseModel
import kotlinx.coroutines.flow.Flow

class LoginUseCases(var loginRepo: LoginRepo) {
    fun login(loginRequestModel: LoginRequestModel): Flow<LoginResponseModel> =
        loginRepo.login(loginRequestModel)
}