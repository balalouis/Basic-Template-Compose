package com.basic.template.network.model

sealed class NetworkResponse<out T> {
    data class Success<out T>(val data: T?) : NetworkResponse<T>()
    data class Failure(val errorMessage: String?) : NetworkResponse<Nothing>()
    object Loading : NetworkResponse<Nothing>()
}