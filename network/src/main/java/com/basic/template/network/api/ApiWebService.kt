package com.basic.template.network.api

import com.basic.template.network.model.LoginRequestModel
import com.basic.template.network.model.LoginResponseModel
import com.basic.template.network.model.RegistrationRequestModel
import com.basic.template.network.model.RegistrationResponseModel
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiWebService {

    @POST("api/login")
    suspend fun loginApiCall(@Body loginRequestModel: LoginRequestModel): LoginResponseModel

    @POST("api/register")
    suspend fun registrationApiCall(@Body registrationRequestModel: RegistrationRequestModel): RegistrationResponseModel


    companion object {
        const val BASE_URL = "https://reqres.in/"
    }
}
