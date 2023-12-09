package fake

import com.basic.template.compose.userlist.data.datasource.UserListDataSource
import com.basic.template.network.model.NetworkResponse
import com.basic.template.network.model.UserListRoot
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeUserListDataSource(
    val isApiSuccess: Boolean = true,
    private val fileName: String = ""
) :
    UserListDataSource {
    override fun fetchUserList(): Flow<NetworkResponse<UserListRoot>> {
        return if (isApiSuccess == true) {
            flow {
                emit(TestDataUtil.getUserListRootSuccessResponse(fileName))
            }
        } else {
            flow {
                emit(TestDataUtil.getFailureResponse())
            }
        }
    }
}
