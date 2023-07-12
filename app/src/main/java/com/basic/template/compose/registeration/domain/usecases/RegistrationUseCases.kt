package com.basic.template.compose.registeration.domain.usecases

import com.basic.template.compose.registeration.domain.repo.RegistrationRepo
import com.basic.template.network.model.RegistrationRequestModel
import com.basic.template.network.model.RegistrationResponseModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RegistrationUseCases @Inject constructor(var registerRepo: RegistrationRepo) {
    fun registration(registrationRequestModel: RegistrationRequestModel): Flow<RegistrationResponseModel> =
        registerRepo.register(registrationRequestModel)
}