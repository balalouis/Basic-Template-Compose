package com.basic.template.network.model

import com.google.gson.annotations.SerializedName

data class UserDetailServerRootData(@SerializedName("data") var user: User?)

