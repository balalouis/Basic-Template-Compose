package com.example.registration

import com.basic.template.network.model.NetworkResponse
import com.basic.template.network.model.RegistrationRequestModel
import com.basic.template.network.model.RegistrationResponseModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RegistrationUseCases @Inject constructor(var registerRepo: RegistrationRepo) {
    fun registration(registrationRequestModel: RegistrationRequestModel): Flow<NetworkResponse<RegistrationResponseModel>> =
        registerRepo.register(registrationRequestModel)
}