package model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class RoomUser(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo("userId")
    var userId: Int? = 0,

    @ColumnInfo("email")
    var email: String? = null,

    @ColumnInfo("first_name")
    var firstName: String? = "",

    @ColumnInfo("last_name")
    var lastName: String? = "",

    @ColumnInfo("avatar")
    var avatar: String? = ""
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
