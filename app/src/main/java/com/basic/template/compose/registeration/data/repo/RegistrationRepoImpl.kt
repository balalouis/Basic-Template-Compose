package com.basic.template.compose.registeration.data.repo

import com.basic.template.compose.registeration.data.datasource.RegistrationDataSource
import com.basic.template.compose.registeration.domain.repo.RegistrationRepo
import com.basic.template.network.model.RegistrationRequestModel
import com.basic.template.network.model.RegistrationResponseModel
import kotlinx.coroutines.flow.Flow

class RegistrationRepoImpl(private val registerDataSource: RegistrationDataSource) : RegistrationRepo{
    override fun register(registrationRequestModel: RegistrationRequestModel): Flow<RegistrationResponseModel> =
        registerDataSource.fetchRegistrationApi(registrationRequestModel)
}