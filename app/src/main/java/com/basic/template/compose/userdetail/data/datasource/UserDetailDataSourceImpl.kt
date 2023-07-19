package com.basic.template.compose.userdetail.data.datasource

import com.basic.template.compose.UserMapper
import com.basic.template.network.api.ApiWebService
import com.basic.template.room.UserDao
import kotlinx.coroutines.flow.Flow
import model.RoomUser

class UserDetailDataSourceImpl(var userDao: UserDao, private val apiWebService: ApiWebService) : UserDetailDataSource {
    override suspend fun fetchAndInsertUserIntoDB(userId: String){
        val userDetail = apiWebService.fetchUserDetail(userId).user
        userDao.insertUser(UserMapper.convertUserToRoomUser(userDetail))
    }

    override fun fetchUserDetailFromDB(userId: Int): Flow<RoomUser?> {
        return userDao.findUserById(userId)
    }

}