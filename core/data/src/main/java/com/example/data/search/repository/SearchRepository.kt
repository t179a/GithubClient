package com.example.data.search.repository

import com.example.data.search.GithubRepositoryItem
import com.example.data.search.GithubUserItem
import com.example.database.model.GithubUser
import kotlinx.collections.immutable.PersistentList
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun searchRepositories(word: String, accessToken: String): Flow<PersistentList<GithubRepositoryItem>>

    fun searchUsers(word: String, accessToken: String): Flow<PersistentList<GithubUserItem>>

    suspend fun saveUser(githubUser: GithubUser)

    suspend fun deleteUser(githubUser: GithubUser)
}
