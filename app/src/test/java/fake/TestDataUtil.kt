package fake

import com.basic.template.compose.UserMapper
import com.basic.template.network.model.LoginResponseModel
import com.basic.template.network.model.NetworkResponse
import com.basic.template.network.model.RegistrationResponseModel
import com.basic.template.network.model.UserDetailServerRootData
import com.basic.template.network.model.UserListRoot
import com.google.gson.GsonBuilder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import model.RoomUser
import java.io.IOException


object TestDataUtil {
    fun getLoginSuccessResponse(): NetworkResponse<LoginResponseModel> =
        NetworkResponse.Success(LoginResponseModel(token = "assccfgvvhnjmkn"))

    fun getRegistrationSuccessResponse(): NetworkResponse<RegistrationResponseModel> =
        NetworkResponse.Success(
            RegistrationResponseModel(token = "assccfgvvhnjmkn")
        )

    fun getFailureResponse() = NetworkResponse.Failure(errorMessage = "in valid password")

    @Throws(IOException::class)

    fun getUserListRootSuccessResponse(value: String): NetworkResponse<UserListRoot> {
        val gson = GsonBuilder().create()
        val success = gson.fromJson(
            value,
            UserListRoot::class.java
        )
        return NetworkResponse.Success(success)
    }

    fun getUserDetailSuccessResponseFromNetwork(value: String): NetworkResponse<RoomUser> {
        val gson = GsonBuilder().create()
        val userDetail = gson.fromJson(
            value,
            UserDetailServerRootData::class.java
        )
        val roomUser = UserMapper.convertUserToRoomUser(userDetail.user)
        return NetworkResponse.Success(roomUser)
    }

    fun getUserDetailSuccessResponseFromDB(value: String): NetworkResponse<RoomUser> {
        val gson = GsonBuilder().create()
        val userDetail = gson.fromJson(
            value,
            UserDetailServerRootData::class.java
        )
        val roomUser = UserMapper.convertUserToRoomUser(userDetail.user)
        return NetworkResponse.Success(roomUser)
    }
}