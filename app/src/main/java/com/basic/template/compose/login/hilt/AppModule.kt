package com.basic.template.compose.login.hilt

import com.basic.template.compose.login.data.datasource.LoginDataSource
import com.basic.template.compose.login.data.datasource.LoginDataSourceImpl
import com.basic.template.compose.login.data.repo.LoginRepoImpl
import com.basic.template.compose.login.domain.repo.LoginRepo
import com.basic.template.compose.login.domain.usecases.LoginUseCases
import com.basic.template.network.api.ApiWebService
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

    fun provideLoginUseCases(loginRepo: LoginRepo): LoginUseCases{
        return LoginUseCases(loginRepo)
    }
}