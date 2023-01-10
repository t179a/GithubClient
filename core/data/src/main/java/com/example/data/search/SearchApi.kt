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
        ).RepositoryList.toPersistentList()
    }
}


