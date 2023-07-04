package com.basic.template.compose.registeration.data.datasource

import com.basic.template.network.model.RegistrationRequestModel
import com.basic.template.network.model.RegistrationResponseModel
import kotlinx.coroutines.flow.Flow

interface RegistrationDataSource {
    fun fetchRegistrationApi(registrationRequestModel: RegistrationRequestModel): Flow<RegistrationResponseModel>
}