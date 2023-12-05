package fake

import com.basic.template.network.model.LoginResponseModel
import com.basic.template.network.model.NetworkResponse

object TestDataUtil {
    fun getLoginSuccessResponse(): NetworkResponse<LoginResponseModel> =
        NetworkResponse.Success(LoginResponseModel(token = "assccfgvvhnjmkn"))

    fun getFailureResponse() = NetworkResponse.Failure(errorMessage = "in valid password")
}