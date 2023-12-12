package fake

import com.basic.template.compose.userdetail.data.datasource.UserDetailDataSource
import com.basic.template.compose.userlist.data.datasource.UserListDataSource
import com.basic.template.network.model.NetworkResponse
import com.basic.template.network.model.UserDetailServerRootData
import com.basic.template.network.model.UserListRoot
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import model.RoomUser

class FakeUserDetailDataSource(
    val isApiSuccess: Boolean = true,
    private val fileName: String = ""
) :
    UserDetailDataSource {

    override fun fetchAndInsertUserIntoDB(userId: String): Flow<NetworkResponse<UserDetailServerRootData>> {
        return if (isApiSuccess == true) {
            flow {
                emit(TestDataUtil.getUserDetailSuccessResponseFromNetwork(fileName))
            }
        } else {
            flow {
                emit(TestDataUtil.getFailureResponse())
            }
        }
    }

    override fun fetchUserDetailFromDB(userId: Int): Flow<RoomUser?> {
        return flow {
            emit(TestDataUtil.getUserDetailSuccessResponseFromDB(fileName))
        }
    }
}
