package com.basic.template.compose.userdetail.domain.usecases

import com.basic.template.compose.userdetail.domain.repo.UserDetailRepo
import kotlinx.coroutines.flow.Flow
import model.RoomUser
import javax.inject.Inject


class UserDetailUseCases @Inject constructor(var userDetailRepo: UserDetailRepo) {
    suspend fun fetchUserAndInsertIntoDB(userId: String){
        userDetailRepo.fetchUserAndInsertIntoDB(userId)
    }

    fun fetchUserDetailFromDB(userId: Int): Flow<RoomUser?> = userDetailRepo.fetchUserDetailFromDB(userId)
}