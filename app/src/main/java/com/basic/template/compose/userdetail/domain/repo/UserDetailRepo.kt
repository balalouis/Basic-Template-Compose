package com.basic.template.compose.userdetail.domain.repo

import com.basic.template.network.model.User
import kotlinx.coroutines.flow.Flow

interface UserDetailRepo {
    fun fetchUserDetail(userId: String): Flow<User?>
}