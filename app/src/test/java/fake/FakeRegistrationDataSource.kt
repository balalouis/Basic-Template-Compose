package fake

import com.basic.template.network.model.NetworkResponse
import com.basic.template.network.model.RegistrationRequestModel
import com.basic.template.network.model.RegistrationResponseModel
import com.basic.template.network.registeration.RegistrationDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRegistrationDataSource(val isApiSuccess: Boolean = true) : RegistrationDataSource {
    override fun fetchRegistrationApi(registrationRequestModel: RegistrationRequestModel): Flow<NetworkResponse<RegistrationResponseModel>> {
        return if (isApiSuccess == true) {
            flow {
                emit(TestDataUtil.getRegistrationSuccessResponse())
            }
        } else {
            flow {
                emit(TestDataUtil.getFailureResponse())
            }
        }
    }
}
