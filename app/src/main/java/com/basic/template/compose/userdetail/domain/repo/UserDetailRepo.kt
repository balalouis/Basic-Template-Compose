package com.basic.template.compose.userdetail.domain.repo

import kotlinx.coroutines.flow.Flow
import model.RoomUser

interface UserDetailRepo {
    suspend fun fetchUserAndInsertIntoDB(userId: String)

    fun fetchUserDetailFromDB(userId: Int): Flow<RoomUser?>
}