package com.example.data.search

import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList

class SearchApi(
    private val networkService: GithubNetworkService
) {
    suspend fun searchRepository(word: String, accessToken: String = ""): PersistentList<GithubRepositoryItem> {
        return networkService.get<SearchRepositoriesResponse>(
            url = "https://api.github.com/search/repositories",
            accessToken = accessToken,
            query = word
        ).repositoryList.toPersistentList()
    }

    suspend fun searchUser(word: String, accessToken: String = ""): PersistentList<GithubUserItem> {
        return networkService.get<SearchUsersResponse>(
            url = "https://api.github.com/search/users",
            accessToken = accessToken,
            query = word
        ).userList.toPersistentList()
    }

    suspend fun getFollowers(userName: String, accessToken: String = ""): PersistentList<GithubUserItem> {
        return networkService.get<List<GithubUserItem>>(
            url = "https://api.github.com/users/$userName/followers",
            accessToken = accessToken
        ).toPersistentList()
    }

    suspend fun getFollowing(userName: String, accessToken: String = ""): PersistentList<GithubUserItem> {
        return networkService.get<List<GithubUserItem>>(
            url = "https://api.github.com/users/$userName/following",
            accessToken = accessToken
        ).toPersistentList()
    }

    suspend fun getUser(userName: String, accessToken: String = ""): GithubUserItem {
        return networkService.get<GithubUserItem>(
            url = "https://api.github.com/users/$userName",
            accessToken = accessToken
        )
    }

    suspend fun getRepositories(userName: String, accessToken: String = ""): PersistentList<GithubRepositoryItem> {
        return networkService.get<List<GithubRepositoryItem>>(
            url = "https://api.github.com/users/$userName/repos",
            accessToken = accessToken
        ).toPersistentList()
    }
}
