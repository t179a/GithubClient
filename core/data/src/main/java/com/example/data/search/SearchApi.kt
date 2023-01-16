package com.example.data.search

import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList

class SearchApi(
    private val networkService: GithubNetworkService
) {
    suspend fun searchRepository(word: String): PersistentList<GithubRepositoryItem> {
        return networkService.get<SearchRepositoriesResponse>(
            url = "https://api.github.com/search/repositories",
            query = word
        ).repositoryList.toPersistentList()
    }

    suspend fun searchUser(word: String): PersistentList<GithubUserItem> {
        return networkService.get<SearchUsersResponse>(
            url = "https://api.github.com/search/users",
            query = word
        ).userList.toPersistentList()
    }

    suspend fun getFollowers(userName: String): PersistentList<GithubUserItem> {
        return networkService.get<List<GithubUserItem>>(
            url = "https://api.github.com/users/${userName}/followers"
        ).toPersistentList()
    }

    suspend fun getFollowing(userName: String): PersistentList<GithubUserItem> {
        return networkService.get<List<GithubUserItem>>(
            url = "https://api.github.com/users/${userName}/following"
        ).toPersistentList()
    }

    suspend fun getUser(userName: String): GithubUserItem {
        return networkService.get<GithubUserItem>(
            url = "https://api.github.com/users/${userName}"
        )
    }

    suspend fun getRepositories(userName: String): PersistentList<GithubRepositoryItem> {
        return networkService.get<List<GithubRepositoryItem>>(
            url = "https://api.github.com/users/${userName}/repos"
        ).toPersistentList()
    }
}


