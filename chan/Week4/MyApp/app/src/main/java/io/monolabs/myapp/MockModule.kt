package io.monolabs.myapp

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MockModule {
    @Singleton
    @Provides
    fun provideRepository(dao: MockUserDao, api: MockUserApi) = UserRepository(dao, api)

    @Singleton
    @Provides
    fun provideMockDAO() = MockUserDao()

    @Singleton
    @Provides
    fun provideMockAPI() = MockUserApi()
}