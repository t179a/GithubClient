package com.example.data.search.di

import com.example.data.search.repository.SearchRepository
import com.example.data.search.repository.SearchRepositoryImpl
import com.example.data.search.repository.UserDetailRepository
import com.example.data.search.repository.UserDetailRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface SearchRepositoryModule {

    @Binds
    fun bindsUserDetailRepository(
        userDetailRepository: UserDetailRepositoryImpl
    ): UserDetailRepository

    @Binds
    fun bindsSearchRepository(
        searchRepository: SearchRepositoryImpl
    ): SearchRepository
}
