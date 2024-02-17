package com.basic.template.compose.hilt

import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton


@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [UrlModule::class]
)
class FakeBaseUrlModule {

    @Provides
    @Singleton
    fun provideUrl(): String = "http://127.0.0.1:8080/"
}