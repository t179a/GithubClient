package com.example.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.database.model.GithubUser

@Dao
interface GithubUserDao {
    @Query("SELECT * FROM githubUser")
    fun getAllUser(): List<GithubUser>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: GithubUser)

    @Delete
    suspend fun delete(user: GithubUser)
}