package com.basic.template.compose.registeration.domain.repo

import com.basic.template.network.model.RegistrationRequestModel
import com.basic.template.network.model.RegistrationResponseModel
import kotlinx.coroutines.flow.Flow

interface RegistrationRepo {
    fun register(registrationRequestModel: RegistrationRequestModel): Flow<RegistrationResponseModel>
}