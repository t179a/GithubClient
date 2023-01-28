package com.example.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.database.dao.GithubUserDao
import com.example.database.model.GithubUser

@Database(entities = [GithubUser::class], version = 1, exportSchema = false)
abstract class GithubDatabase: RoomDatabase() {
    abstract fun githubUserDao(): GithubUserDao
}