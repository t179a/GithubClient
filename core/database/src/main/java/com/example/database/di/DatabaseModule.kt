package com.example.database.di

import android.content.Context
import androidx.room.Room
import com.example.database.GithubDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): GithubDatabase = Room.databaseBuilder(
        context,
        GithubDatabase::class.java,
        "github-database"
    ).build()
}