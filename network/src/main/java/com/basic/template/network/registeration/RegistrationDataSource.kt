package com.basic.template.network.registeration

import com.basic.template.network.model.NetworkResponse
import com.basic.template.network.model.RegistrationRequestModel
import com.basic.template.network.model.RegistrationResponseModel
import kotlinx.coroutines.flow.Flow

interface RegistrationDataSource {
    fun fetchRegistrationApi(registrationRequestModel: RegistrationRequestModel): Flow<NetworkResponse<RegistrationResponseModel>>
}