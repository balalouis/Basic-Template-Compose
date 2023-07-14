package model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class RoomUser(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo("email")
    var userEmail: String? = null,

    @ColumnInfo("first_name")
    var userFirstName: String = "",

    @ColumnInfo("last_name")
    var userLastName: String = "",

    @ColumnInfo("avatar")
    var userAvatar: String = ""
)


data class UserListRoot(
    @ColumnInfo("room_user_list")
    var userModelList: List<RoomUser>? = null
)

data class SingleUser(@ColumnInfo("room_user")var user: RoomUser?) {
    companion object

}

sealed class UserUIState {
    data class Success(var userList: List<RoomUser>?) : UserUIState()
    data class Failure(var exception: Throwable) : UserUIState()
}

sealed class UserDetailUIState {
    data class Success(var user: RoomUser?) : UserDetailUIState()
    data class Failure(var exception: Throwable) : UserDetailUIState()
}