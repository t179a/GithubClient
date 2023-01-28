package com.example.data.favorite.di

import com.example.data.favorite.FavoriteRepository
import com.example.database.dao.GithubUserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class FavoriteModule {
    @Provides
    @Singleton
    fun provideFavoriteRepository(githubUserDao: GithubUserDao): FavoriteRepository {
        return FavoriteRepository(githubUserDao = githubUserDao)
    }
}