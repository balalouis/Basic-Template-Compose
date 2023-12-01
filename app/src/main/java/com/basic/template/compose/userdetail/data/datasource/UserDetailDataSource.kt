package com.basic.template.compose.userdetail.data.datasource

import com.basic.template.network.model.NetworkResponse
import com.basic.template.network.model.UserDetailServerRootData
import kotlinx.coroutines.flow.Flow
import model.RoomUser

interface UserDetailDataSource {
    fun fetchAndInsertUserIntoDB(userId: String): Flow<NetworkResponse<UserDetailServerRootData>>

    fun fetchUserDetailFromDB(userId: Int): Flow<RoomUser?>
}