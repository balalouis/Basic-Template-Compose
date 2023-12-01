package com.basic.template.compose.userdetail.domain.repo

import com.basic.template.network.model.NetworkResponse
import com.basic.template.network.model.UserDetailServerRootData
import kotlinx.coroutines.flow.Flow
import model.RoomUser

interface UserDetailRepo {
    fun fetchUserAndInsertIntoDB(userId: String): Flow<NetworkResponse<UserDetailServerRootData>>

    fun fetchUserDetailFromDB(userId: Int): Flow<RoomUser?>
}