package com.example.data.search.repository

import com.example.data.search.GithubRepositoryItem
import com.example.data.search.GithubUserItem
import com.example.data.search.SearchApi
import com.example.database.dao.GithubUserDao
import com.example.database.model.GithubUser
import kotlinx.collections.immutable.PersistentList
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchApi: SearchApi,
    private val githubUserDao: GithubUserDao
) : SearchRepository {
    override fun searchRepositories(
        word: String,
        accessToken: String
    ): Flow<PersistentList<GithubRepositoryItem>> {
        return callbackFlow {
            send(searchApi.searchRepository(word = word, accessToken = accessToken))
            awaitClose {}
        }
    }

    override fun searchUsers(word: String, accessToken: String): Flow<PersistentList<GithubUserItem>> {
        return callbackFlow {
            send(searchApi.searchUser(word = word, accessToken = accessToken))
            awaitClose {}
        }
    }

    override suspend fun saveUser(githubUser: GithubUser) {
        githubUserDao.insertUser(githubUser)
    }

    override suspend fun deleteUser(githubUser: GithubUser) {
        githubUserDao.delete(githubUser)
    }
}
