package com.example.data.search

import com.example.database.dao.GithubUserDao
import com.example.database.model.GithubUser
import kotlinx.collections.immutable.PersistentList
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class SearchRepository(
    private val searchApi: SearchApi,
    private val githubUserDao: GithubUserDao
) {
    fun searchRepositories(
        word: String,
        accessToken: String
    ): Flow<PersistentList<GithubRepositoryItem>> {
        return callbackFlow {
            send(searchApi.searchRepository(word = word, accessToken = accessToken))
            awaitClose {}
        }
    }

    fun searchUsers(word: String, accessToken: String): Flow<PersistentList<GithubUserItem>> {
        return callbackFlow {
            send(searchApi.searchUser(word = word, accessToken = accessToken))
            awaitClose {}
        }
    }

    suspend fun saveUser(githubUser: GithubUser) {
        githubUserDao.insertUser(githubUser)
    }

    suspend fun deleteUser(githubUser: GithubUser) {
        githubUserDao.delete(githubUser)
    }
}
