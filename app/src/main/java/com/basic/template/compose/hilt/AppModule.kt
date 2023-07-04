package com.basic.template.compose.hilt

import com.basic.template.compose.login.data.datasource.LoginDataSource
import com.basic.template.compose.login.data.datasource.LoginDataSourceImpl
import com.basic.template.compose.login.data.repo.LoginRepoImpl
import com.basic.template.compose.login.domain.repo.LoginRepo
import com.basic.template.compose.login.domain.usecases.LoginUseCases
import com.basic.template.compose.registeration.data.datasource.RegistrationDataSource
import com.basic.template.compose.registeration.data.datasource.RegistrationDataSourceImpl
import com.basic.template.compose.registeration.data.repo.RegistrationRepoImpl
import com.basic.template.compose.registeration.domain.repo.RegistrationRepo
import com.basic.template.compose.registeration.domain.usecases.RegistrationUseCases
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

    @Provides
    fun provideRegisterDataSource(apiWebService: ApiWebService): RegistrationDataSource {
        return RegistrationDataSourceImpl(apiWebService)
    }

    @Provides
    fun provideRegisterRepository(registrationDataSource: RegistrationDataSource): RegistrationRepo {
        return RegistrationRepoImpl(registrationDataSource)
    }

    fun provideRegisterUseCases(registrationRepo: RegistrationRepo): RegistrationUseCases{
        return RegistrationUseCases(registrationRepo)
    }
}