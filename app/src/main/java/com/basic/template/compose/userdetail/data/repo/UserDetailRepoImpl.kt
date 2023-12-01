package com.basic.template.compose.userdetail.data.repo

import com.basic.template.compose.userdetail.data.datasource.UserDetailDataSource
import com.basic.template.compose.userdetail.domain.repo.UserDetailRepo
import kotlinx.coroutines.flow.Flow
import model.RoomUser

class UserDetailRepoImpl(private var userDetailDataSource: UserDetailDataSource) : UserDetailRepo {
    override fun fetchUserAndInsertIntoDB(userId: String) =
        userDetailDataSource.fetchAndInsertUserIntoDB(userId)


    override fun fetchUserDetailFromDB(userId: Int): Flow<RoomUser?> =
        userDetailDataSource.fetchUserDetailFromDB(userId = userId)

}