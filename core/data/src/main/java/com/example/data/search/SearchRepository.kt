package com.example.data.search

import kotlinx.collections.immutable.PersistentList
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class SearchRepository(
    private val searchApi: SearchApi
) {
    fun searchRepository(word: String): Flow<PersistentList<GithubRepositoryItem>> {
        return callbackFlow {
            send(searchApi.searchRepository(word))
            awaitClose {}
        }
    }

    fun searchUsers(word: String): Flow<PersistentList<GithubUserItem>> {
        return callbackFlow {
            send(searchApi.searchUser(word))
            awaitClose {}
        }
    }
}