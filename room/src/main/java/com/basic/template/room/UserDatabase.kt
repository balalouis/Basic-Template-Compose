package com.basic.template.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.basic.template.room.UserDao
import model.RoomUser

@Database(entities = [RoomUser::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}