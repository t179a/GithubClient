package com.example.data.search

import kotlinx.collections.immutable.PersistentList
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class UserDetailRepository(
    private val searchApi: SearchApi
) {
    fun getFollowers(userName: String): Flow<PersistentList<UserItem>> {
        return callbackFlow {
            send(searchApi.getFollowers(userName))
            awaitClose {}
        }
    }
    fun getFollowing(userName: String): Flow<PersistentList<UserItem>> {
        return callbackFlow {
            send(searchApi.getFollowing(userName))
            awaitClose {}
        }
    }
}