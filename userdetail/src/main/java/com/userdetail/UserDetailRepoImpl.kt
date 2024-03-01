package com.userdetail

import com.basic.template.network.userdetail.UserDetailDataSource
import kotlinx.coroutines.flow.Flow
import model.RoomUser

class UserDetailRepoImpl(private var userDetailDataSource: UserDetailDataSource) : UserDetailRepo {
    override fun fetchUserAndInsertIntoDB(userId: String) =
        userDetailDataSource.fetchAndInsertUserIntoDB(userId)


    override fun fetchUserDetailFromDB(userId: Int): Flow<RoomUser?> =
        userDetailDataSource.fetchUserDetailFromDB(userId = userId)

}