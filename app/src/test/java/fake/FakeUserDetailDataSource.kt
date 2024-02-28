package fake

import com.basic.template.network.model.NetworkResponse
import com.basic.template.network.model.UserDetailServerRootData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import model.RoomUser
import com.basic.template.network.userdetail.UserDetailDataSource

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
