package com.basic.template.network.model

data class RegistrationRequestModel(val email: String, val password: String)

data class RegistrationResponseModel(val id: Int = 0, val token: String? = "")
