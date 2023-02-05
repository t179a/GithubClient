package com.example.data.search.repository

import com.example.data.search.GithubRepositoryItem
import com.example.data.search.GithubUserItem
import com.example.data.search.SearchApi
import kotlinx.collections.immutable.PersistentList
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class UserDetailRepositoryImpl @Inject constructor(
    private val searchApi: SearchApi
) : UserDetailRepository {
    override fun getFollowers(userName: String): Flow<PersistentList<GithubUserItem>> {
        return callbackFlow {
            send(searchApi.getFollowers(userName))
            awaitClose {}
        }
    }
    override fun getFollowing(userName: String): Flow<PersistentList<GithubUserItem>> {
        return callbackFlow {
            send(searchApi.getFollowing(userName))
            awaitClose {}
        }
    }
    override fun getUser(word: String): Flow<GithubUserItem> {
        return callbackFlow {
            send(searchApi.getUser(word))
            awaitClose {}
        }
    }

    override fun getRepositories(userName: String): Flow<PersistentList<GithubRepositoryItem>> {
        return callbackFlow {
            send(searchApi.getRepositories(userName))
            awaitClose {}
        }
    }
}
