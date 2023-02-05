package com.example.data.search.repository

import com.example.data.search.GithubRepositoryItem
import com.example.data.search.GithubUserItem
import kotlinx.collections.immutable.PersistentList
import kotlinx.coroutines.flow.Flow

interface UserDetailRepository {
    fun getFollowers(userName: String): Flow<PersistentList<GithubUserItem>>

    fun getFollowing(userName: String): Flow<PersistentList<GithubUserItem>>

    fun getUser(word: String): Flow<GithubUserItem>

    fun getRepositories(userName: String): Flow<PersistentList<GithubRepositoryItem>>
}
