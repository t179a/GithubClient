package com.example.data.search.di

import com.example.data.search.SearchApi
import com.example.data.search.SearchNetworkService
import com.example.data.search.SearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class SearchModule {
    @Provides
    @Singleton
    fun provideSearchRepository(searchApi: SearchApi): SearchRepository {
        return SearchRepository(searchApi)
    }

    @Provides
    @Singleton
    fun provideSearchApi(searchNetworkService: SearchNetworkService): SearchApi {
        return SearchApi(searchNetworkService)
    }

    @Provides
    @Singleton
    fun provideSearchNetworkService(
        httpClient: HttpClient
    ): SearchNetworkService {
        return SearchNetworkService(httpClient)
    }
}