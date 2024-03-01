package com.example.userlist

import com.basic.template.network.model.NetworkResponse
import com.basic.template.network.model.UserListRoot
import com.basic.template.network.userlist.UserListDataSource
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
