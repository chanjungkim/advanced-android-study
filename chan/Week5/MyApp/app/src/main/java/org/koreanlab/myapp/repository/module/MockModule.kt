package org.koreanlab.myapp.repository.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.koreanlab.myapp.repository.UserRepository
import org.koreanlab.myapp.repository.remote.MockUserApi
import org.koreanlab.myapp.repository.room.MockUserDao
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