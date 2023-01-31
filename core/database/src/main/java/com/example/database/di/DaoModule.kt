package com.example.database.di

import com.example.database.GithubDatabase
import com.example.database.dao.GithubUserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DaoModule {
    @Provides
    fun providesTopicsDao(
        database: GithubDatabase
    ): GithubUserDao = database.githubUserDao()
}
