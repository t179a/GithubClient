package com.example.data.favorite

import com.example.database.dao.GithubUserDao
import com.example.database.model.GithubUser
import kotlinx.coroutines.flow.Flow

class FavoriteRepository(
    private val githubUserDao: GithubUserDao
) {
    fun getAllUser(): Flow<List<GithubUser>> {
        return githubUserDao.getAllUser()
    }

    suspend fun deleteUser(githubUser: GithubUser) {
        githubUserDao.delete(githubUser)
    }
}
