package com.basic.template.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import model.RoomUser

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: RoomUser?)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUserList(user: List<RoomUser>?)

    @Delete
    suspend fun deleteUserByModel(user: RoomUser)

    @Query("DELETE FROM user_table WHERE id = :id")
    suspend fun deleteUserByID(id: Int)

    @Query("DELETE FROM user_table")
    suspend fun deleteAllUser()

    @Query("SELECT * FROM user_table WHERE id = :id")
    fun findUserById(id: Int): Flow<RoomUser>

    @Query("SELECT * FROM user_table WHERE userFirstName LIKE :firstName AND " + "userLastName LIKE :lastName LIMIT 1")
    fun findUserByName(firstName: String, lastName: String): Flow<RoomUser>

    @Query("SELECT * FROM user_table")
    fun getAllUser(): Flow<List<RoomUser>>

}