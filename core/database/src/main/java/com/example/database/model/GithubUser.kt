package com.example.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GithubUser(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "user_name") val userName: String,
    @ColumnInfo(name = "avatar_url") val avatarUrl: String
)