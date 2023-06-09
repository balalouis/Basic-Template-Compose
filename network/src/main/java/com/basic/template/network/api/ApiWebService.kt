package com.basic.template.network.api

import com.basic.template.network.model.LoginRequestModel
import com.basic.template.network.model.LoginResponseModel
import com.basic.template.network.model.RegistrationRequestModel
import com.basic.template.network.model.RegistrationResponseModel
import com.basic.template.network.model.UserDetailServerRootData
import com.basic.template.network.model.UserListRoot
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiWebService {

    @POST("api/login")
    suspend fun loginApiCall(@Body loginRequestModel: LoginRequestModel): LoginResponseModel

    @POST("api/register")
    suspend fun registrationApiCall(@Body registrationRequestModel: RegistrationRequestModel): RegistrationResponseModel

    @GET("api/users?page=2")
    suspend fun fetchUserList(): UserListRoot

    @GET("api/users/{id}")
    suspend fun fetchUserDetail(@Path("id") id: String): UserDetailServerRootData

    companion object {
        const val BASE_URL = "https://reqres.in/"
    }
}
