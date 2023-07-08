package com.basic.template.compose.userdetail.data.datasource

import com.basic.template.network.model.User
import kotlinx.coroutines.flow.Flow

interface UserDetailDataSource {
    fun fetchUserDetail(userId: String): Flow<User?>
}