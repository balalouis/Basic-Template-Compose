package com.basic.template.network.api

import com.basic.template.network.model.NetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

abstract class BaseApi {
    suspend fun <T> safeApiCall(apiToBeCalled: suspend () -> Response<T>): NetworkResponse<T> {
        return withContext(Dispatchers.IO) {
            try {
                val response: Response<T> = apiToBeCalled()

                if (response.isSuccessful) {
                    NetworkResponse.Success(data = response.body()!!)
                } else {
                    NetworkResponse.Failure(
                        errorMessage = response.message()
                    )
                }

            } catch (e: HttpException) {
                NetworkResponse.Failure(errorMessage = e.message ?: "Something went wrong")
            } catch (e: IOException) {
                // Returning no internet message
                // wrapped in Resource.Error
                NetworkResponse.Failure("Please check your network connection")
            } catch (e: Exception) {
                // Returning 'Something went wrong' in case
                // of unknown error wrapped in Resource.Error
                NetworkResponse.Failure(errorMessage = "Something went wrong")
            }
        }
    }
}