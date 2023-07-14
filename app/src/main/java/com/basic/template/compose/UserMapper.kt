package com.basic.template.compose

import com.basic.template.network.model.User
import model.RoomUser

object UserMapper {
    fun convertUserToRoomUser(user: User): RoomUser =
        RoomUser(
            email = user.userEmail,
            firstName = user.userFirstName,
            lastName = user.userLastName,
            avatar = user.userAvatar
        )

    fun convertUserListToRoomUserList(userList: List<User>): List<RoomUser> {
        val userRoomList: MutableList<RoomUser> = mutableListOf()
        for (user in userList) {
            userRoomList.add(convertUserToRoomUser(user = user))
        }
        return userRoomList.toList()
    }
}