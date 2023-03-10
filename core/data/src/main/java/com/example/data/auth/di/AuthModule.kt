package com.example.data.auth.di

import com.example.data.auth.AuthApi
import com.example.data.auth.AuthRepository
import com.example.data.search.GithubNetworkService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AuthModule {
    @Provides
    @Singleton
    fun provideAuthRepository(authApi: AuthApi): AuthRepository {
        return AuthRepository(authApi)
    }

    @Provides
    @Singleton
    fun provideAuthApi(githubNetworkService: GithubNetworkService): AuthApi {
        return AuthApi(githubNetworkService)
    }
}
