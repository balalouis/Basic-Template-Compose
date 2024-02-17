package com.basic.template.compose.hilt

import com.basic.template.network.api.ApiWebService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UrlModule {
    @Provides
    @Singleton
    fun provideUrl(): String = ApiWebService.BASE_URL

}