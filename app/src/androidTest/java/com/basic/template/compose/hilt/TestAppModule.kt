package com.basic.template.compose.hilt

import com.basic.template.network.api.ApiWebService
import com.basic.template.network.login.LoginDataSource
import com.basic.template.network.login.LoginDataSourceImpl
import com.basic.template.network.registeration.RegistrationDataSource
import com.basic.template.network.registeration.RegistrationDataSourceImpl
import com.basic.template.network.userdetail.UserDetailDataSource
import com.basic.template.network.userdetail.UserDetailDataSourceImpl
import com.basic.template.network.userlist.UserListDataSource
import com.basic.template.network.userlist.UserListDataSourceImpl
import com.basic.template.room.UserDao
import com.example.registration.RegistrationRepo
import com.example.registration.RegistrationRepoImpl
import com.example.userlist.UserListRepo
import com.example.userlist.UserListRepoImpl
import com.login.LoginRepo
import com.login.LoginRepoImpl
import com.userdetail.UserDetailRepo
import com.userdetail.UserDetailRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
    components = [SingletonComponent::class, ViewModelComponent::class],
    replaces = [AppModule::class]
)
class TestAppModule {

    @Provides
    fun provideLoginDataSource(apiWebService: ApiWebService): LoginDataSource {
        return LoginDataSourceImpl(apiWebService)
    }

    @Provides
    fun provideLoginRepository(loginDataSource: LoginDataSource): LoginRepo {
        return LoginRepoImpl(loginDataSource)
    }

    @Provides
    fun provideRegistrationDataSource(apiWebService: ApiWebService): RegistrationDataSource {
        return RegistrationDataSourceImpl(apiWebService)
    }

    @Provides
    fun provideRegistrationRepository(registrationDataSource: RegistrationDataSource): RegistrationRepo {
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
    fun provideUserDetailDataSource(
        userDao: UserDao,
        apiWebService: ApiWebService
    ): UserDetailDataSource {
        return UserDetailDataSourceImpl(userDao, apiWebService)
    }

    @Provides
    fun provideUserDetailRepository(userDetailDataSource: UserDetailDataSource): UserDetailRepo {
        return UserDetailRepoImpl(userDetailDataSource)
    }
}