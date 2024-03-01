package com.login

import com.basic.template.network.model.LoginResponseModel
import com.basic.template.network.model.NetworkResponse
import com.basic.template.network.model.RegistrationResponseModel
import com.basic.template.network.model.UserDetailServerRootData
import com.basic.template.network.model.UserListRoot
import com.google.gson.GsonBuilder
import model.RoomUser
import java.io.IOException


object TestDataUtil {
    val errorMessage: String = "In valid password"
    fun getLoginSuccessResponse(): NetworkResponse<LoginResponseModel> =
        NetworkResponse.Success(LoginResponseModel(token = "assccfgvvhnjmkn"))

    fun getRegistrationSuccessResponse(): NetworkResponse<RegistrationResponseModel> =
        NetworkResponse.Success(
            RegistrationResponseModel(token = "assccfgvvhnjmkn")
        )

    fun getFailureResponse() = NetworkResponse.Failure(errorMessage)

    @Throws(IOException::class)

    fun getUserListRootSuccessResponse(value: String): NetworkResponse<UserListRoot> {
        val gson = GsonBuilder().create()
        val success = gson.fromJson(
            value,
            UserListRoot::class.java
        )
        return NetworkResponse.Success(success)
    }

    fun getUserDetailSuccessResponseFromNetwork(value: String): NetworkResponse<UserDetailServerRootData> {
        val gson = GsonBuilder().create()
        val userDetail = gson.fromJson(
            value,
            UserDetailServerRootData::class.java
        )
        return NetworkResponse.Success(userDetail)
    }

    fun getUserDetailSuccessResponseFromDB(value: String): RoomUser {
        val gson = GsonBuilder().create()
        val userDetail = gson.fromJson(
            value,
            UserDetailServerRootData::class.java
        )
        return UserMapper.convertUserToRoomUser(userDetail.user)
    }
}