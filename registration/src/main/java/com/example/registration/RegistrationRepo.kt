package com.example.registration

import com.basic.template.network.model.NetworkResponse
import com.basic.template.network.model.RegistrationRequestModel
import com.basic.template.network.model.RegistrationResponseModel
import kotlinx.coroutines.flow.Flow

interface RegistrationRepo {
    fun register(registrationRequestModel: RegistrationRequestModel): Flow<NetworkResponse<RegistrationResponseModel>>
}