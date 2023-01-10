package com.example.data.search

import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList

class SearchApi(
    private val networkService: SearchNetworkService
) {
    suspend fun searchRepository(word: String): PersistentList<RepositoryItem> {
        return networkService.get<SearchRepositoriesResponse>(
            url = "https://api.github.com/search/repositories",
            query = word
        ).repositoryList.toPersistentList()
    }

    suspend fun searchUser(word: String): PersistentList<UserItem> {
        return networkService.get<SearchUsersResponse>(
            url = "https://api.github.com/search/users",
            query = word
        ).userList.toPersistentList()
    }
}


