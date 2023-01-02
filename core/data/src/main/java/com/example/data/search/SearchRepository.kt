package com.example.data.search

import kotlinx.collections.immutable.PersistentList
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class SearchRepository(
    private val searchApi: SearchApi
) {
    fun search(word: String): Flow<PersistentList<RepositoryItem>> {
        return callbackFlow {
            send(searchApi.searchRepository(word))
            awaitClose {}
        }
    }
}