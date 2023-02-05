package com.example.testing.repository

import com.example.data.search.GithubRepositoryItem
import com.example.data.search.GithubUserItem
import com.example.data.search.repository.SearchRepository
import com.example.testing.data.githubUserItemTestData
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class TestSearchRepository : SearchRepository {

    private val userItemsFlow: Flow<PersistentList<GithubUserItem>> =
        flowOf(githubUserItemTestData.toPersistentList())

    override fun searchRepositories(
        word: String,
        accessToken: String
    ): Flow<PersistentList<GithubRepositoryItem>> {
        TODO("Not yet implemented")
    }

    override fun searchUsers(
        word: String,
        accessToken: String
    ): Flow<PersistentList<GithubUserItem>> {
        return userItemsFlow
    }

    override suspend fun saveUser(githubUser: com.example.database.model.GithubUser) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteUser(githubUser: com.example.database.model.GithubUser) {
        TODO("Not yet implemented")
    }

}