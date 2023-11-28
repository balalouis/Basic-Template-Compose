package com.basic.template.network.model

data class LoginRequestModel(val email: String, val password: String)

data class LoginResponseModel(val token: String?)
