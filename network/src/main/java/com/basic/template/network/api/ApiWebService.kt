package com.basic.template.network.api

import com.basic.template.network.model.LoginRequestModel
import com.basic.template.network.model.LoginResponseModel
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiWebService {

    @POST("api/login")
    suspend fun loginApiCall(@Body loginRequestModel: LoginRequestModel): LoginResponseModel

    companion object {
        const val BASE_URL = "https://reqres.in/"
    }
}
