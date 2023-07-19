package com.basic.template.compose.userdetail.data.datasource

import kotlinx.coroutines.flow.Flow
import model.RoomUser

interface UserDetailDataSource {
    suspend fun fetchAndInsertUserIntoDB(userId: String)

    fun fetchUserDetailFromDB(userId: Int): Flow<RoomUser?>
}