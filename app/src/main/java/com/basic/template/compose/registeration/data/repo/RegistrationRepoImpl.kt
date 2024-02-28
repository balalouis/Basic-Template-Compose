package com.basic.template.compose.registeration.data.repo

import com.basic.template.compose.registeration.domain.repo.RegistrationRepo
import com.basic.template.network.model.NetworkResponse
import com.basic.template.network.model.RegistrationRequestModel
import com.basic.template.network.model.RegistrationResponseModel
import com.basic.template.network.registeration.RegistrationDataSource
import kotlinx.coroutines.flow.Flow

class RegistrationRepoImpl(private val registerDataSource: RegistrationDataSource) : RegistrationRepo{
    override fun register(registrationRequestModel: RegistrationRequestModel): Flow<NetworkResponse<RegistrationResponseModel>> =
        registerDataSource.fetchRegistrationApi(registrationRequestModel)
}