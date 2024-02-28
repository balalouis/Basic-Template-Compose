package com.basic.template.network.userdetail

import android.util.Log
import com.basic.template.network.api.ApiWebService
import com.basic.template.network.api.BaseApi
import com.basic.template.network.model.NetworkResponse
import com.basic.template.network.model.UserDetailServerRootData
import com.basic.template.room.UserDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import model.RoomUser

class UserDetailDataSourceImpl(
    private var userDao: UserDao,
    private val apiWebService: ApiWebService
) :
    UserDetailDataSource, BaseApi() {
    override fun fetchAndInsertUserIntoDB(userId: String): Flow<NetworkResponse<UserDetailServerRootData>> {
        return flow {
            val responseState = safeApiCall { apiWebService.fetchUserDetail(userId) }

            when (responseState) {
                is NetworkResponse.Success -> {
                    val userDetail = responseState.data?.user
                    userDao.insertUser(UserMapper.convertUserToRoomUser(userDetail))
                }

                is NetworkResponse.Failure -> {
                    val errorMessage = responseState.errorMessage
                    Log.d("", "$errorMessage")
                }

                else -> {
                    Log.d("", "")
                }
            }
            emit(responseState)
        }.flowOn(Dispatchers.IO)
    }

    override fun fetchUserDetailFromDB(userId: Int): Flow<RoomUser?> {
        return userDao.findUserById(userId)
    }

}