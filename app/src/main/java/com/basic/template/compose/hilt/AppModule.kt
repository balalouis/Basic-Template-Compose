package com.basic.template.compose.hilt

import com.basic.template.compose.login.data.repo.LoginRepoImpl
import com.basic.template.compose.login.domain.repo.LoginRepo
import com.basic.template.compose.registeration.data.datasource.RegistrationDataSource
import com.basic.template.compose.registeration.data.datasource.RegistrationDataSourceImpl
import com.basic.template.compose.registeration.data.repo.RegistrationRepoImpl
import com.basic.template.compose.registeration.domain.repo.RegistrationRepo
import com.basic.template.compose.userdetail.data.datasource.UserDetailDataSource
import com.basic.template.compose.userdetail.data.datasource.UserDetailDataSourceImpl
import com.basic.template.compose.userdetail.data.repo.UserDetailRepoImpl
import com.basic.template.compose.userdetail.domain.repo.UserDetailRepo
import com.basic.template.compose.userlist.data.datasource.UserListDataSource
import com.basic.template.compose.userlist.data.datasource.UserListDataSourceImpl
import com.basic.template.compose.userlist.data.repo.UserListRepoImpl
import com.basic.template.compose.userlist.domain.repo.UserListRepo
import com.basic.template.network.api.ApiWebService
import com.basic.template.network.login.LoginDataSource
import com.basic.template.network.login.LoginDataSourceImpl
import com.basic.template.room.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class AppModule {

    @Provides
    fun provideLoginDataSource(apiWebService: ApiWebService): LoginDataSource {
        return LoginDataSourceImpl(apiWebService)
    }

    @Provides
    fun provideLoginRepository(loginDataSource: LoginDataSource): LoginRepo {
        return LoginRepoImpl(loginDataSource)
    }

    @Provides
    fun provideRegisterDataSource(apiWebService: ApiWebService): RegistrationDataSource {
        return RegistrationDataSourceImpl(apiWebService)
    }

    @Provides
    fun provideRegisterRepository(registrationDataSource: RegistrationDataSource): RegistrationRepo {
        return RegistrationRepoImpl(registrationDataSource)
    }

    @Provides
    fun provideUserListDataSource(apiWebService: ApiWebService): UserListDataSource {
        return UserListDataSourceImpl(apiWebService)
    }

    @Provides
    fun provideUserListRepository(userListDataSource: UserListDataSource): UserListRepo {
        return UserListRepoImpl(userListDataSource)
    }

    @Provides
    fun provideUserDetailDataSource(userDao: UserDao, apiWebService: ApiWebService): UserDetailDataSource {
        return UserDetailDataSourceImpl(userDao, apiWebService)
    }

    @Provides
    fun provideUserDetailRepository(userDetailDataSource: UserDetailDataSource): UserDetailRepo {
        return UserDetailRepoImpl(userDetailDataSource)
    }
}