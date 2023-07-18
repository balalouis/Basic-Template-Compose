package com.basic.template.compose.userdetail.data.datasource

import com.basic.template.compose.UserMapper
import com.basic.template.network.api.ApiWebService
import com.basic.template.network.model.User
import com.basic.template.room.UserDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class UserDetailDataSourceImpl(var userDao: UserDao, private val apiWebService: ApiWebService) : UserDetailDataSource {
    override fun fetchUserDetail(userId: String): Flow<User?> = flow {
        val userDetail = apiWebService.fetchUserDetail(userId).user
        userDao.insertUser(UserMapper.convertUserToRoomUser(userDetail))
        emit(userDetail)
    }.flowOn(Dispatchers.IO)
}