package fake

import com.basic.template.network.model.LoginResponseModel
import com.basic.template.network.model.NetworkResponse
import com.basic.template.network.model.RegistrationResponseModel

object TestDataUtil {
    fun getLoginSuccessResponse(): NetworkResponse<LoginResponseModel> =
        NetworkResponse.Success(LoginResponseModel(token = "assccfgvvhnjmkn"))

    fun getRegistrationSuccessResponse(): NetworkResponse<RegistrationResponseModel> =
        NetworkResponse.Success(
            RegistrationResponseModel(token = "assccfgvvhnjmkn")
        )

    fun getFailureResponse() = NetworkResponse.Failure(errorMessage = "in valid password")
}