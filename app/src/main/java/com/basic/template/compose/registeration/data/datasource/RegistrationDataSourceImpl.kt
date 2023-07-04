package com.basic.template.compose.registeration.data.datasource

import com.basic.template.network.api.ApiWebService
import com.basic.template.network.model.RegistrationRequestModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RegistrationDataSourceImpl(private val apiWebService: ApiWebService) : RegistrationDataSource {
    override fun fetchRegistrationApi(registrationRequestModel: RegistrationRequestModel) = flow {
        val registrationResponseModel = apiWebService.registrationApiCall(registrationRequestModel)
        emit(registrationResponseModel)
    }.flowOn(Dispatchers.IO)
}
