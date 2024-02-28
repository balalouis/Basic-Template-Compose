package fake

import com.basic.template.network.login.LoginDataSource
import com.basic.template.network.model.LoginRequestModel
import com.basic.template.network.model.LoginResponseModel
import com.basic.template.network.model.NetworkResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeLoginDataSource(val isApiSuccess: Boolean = true) : LoginDataSource {
    override fun fetchLoginApi(loginRequestModel: LoginRequestModel): Flow<NetworkResponse<LoginResponseModel>> {
        return if (isApiSuccess == true) {
            flow {
                emit(TestDataUtil.getLoginSuccessResponse())
            }
        } else {
            flow {
                emit(TestDataUtil.getFailureResponse())
            }
        }
    }
}
