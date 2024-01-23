package com.basic.template.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import model.RoomUser

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: RoomUser)

    @Query("SELECT * FROM user_table WHERE userId = :userIdArgs")
    fun findUserById(userIdArgs: Int): Flow<RoomUser>

}