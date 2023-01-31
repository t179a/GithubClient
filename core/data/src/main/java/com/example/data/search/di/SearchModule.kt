package com.example.data.search.di

import com.example.data.search.GithubNetworkService
import com.example.data.search.SearchApi
import com.example.data.search.SearchRepository
import com.example.data.search.UserDetailRepository
import com.example.database.dao.GithubUserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class SearchModule {
    @Provides
    @Singleton
    fun provideUserDetailRepository(searchApi: SearchApi): UserDetailRepository {
        return UserDetailRepository(searchApi)
    }

    @Provides
    @Singleton
    fun provideSearchRepository(searchApi: SearchApi, githubUserDao: GithubUserDao): SearchRepository {
        return SearchRepository(searchApi = searchApi, githubUserDao = githubUserDao)
    }

    @Provides
    @Singleton
    fun provideSearchApi(searchNetworkService: GithubNetworkService): SearchApi {
        return SearchApi(searchNetworkService)
    }

    @Provides
    @Singleton
    fun provideSearchNetworkService(
        httpClient: HttpClient
    ): GithubNetworkService {
        return GithubNetworkService(httpClient)
    }
}
