package com.basic.template.network.userdetail

import com.basic.template.network.model.User
import model.RoomUser

object UserMapper {
    fun convertUserToRoomUser(user: User?): RoomUser =
        RoomUser(
            userId = user?.id,
            email = user?.userEmail,
            firstName = user?.userFirstName,
            lastName = user?.userLastName,
            avatar = user?.userAvatar
        )
}