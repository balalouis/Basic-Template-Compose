package com.basic.template.compose.login.data.repo

import com.basic.template.compose.login.data.datasource.LoginDataSource
import com.basic.template.compose.login.domain.repo.LoginRepo
import com.basic.template.network.model.LoginRequestModel
import com.basic.template.network.model.LoginResponseModel
import kotlinx.coroutines.flow.Flow

class LoginRepoImpl(var loginDataSource: LoginDataSource):LoginRepo {
    override fun login(loginRequestModel: LoginRequestModel): Flow<LoginResponseModel> =
        loginDataSource.fetchLoginApi(loginRequestModel)
}