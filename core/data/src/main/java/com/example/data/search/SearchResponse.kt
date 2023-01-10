package com.example.data.search

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class SearchRepositoriesResponse(
    @SerialName("items")
    val repositoryList: List<RepositoryItem>
)

@Serializable
data class RepositoryItem(
    val id: Long,
    @SerialName("full_name")
    val name: String,
    val owner: Owner,
    val language: String?,
    @SerialName("stargazers_count")
    val stargazersCount: Long,
    @SerialName("watchers_count")
    val watchersCount: Long,
    @SerialName("forks_count")
    val forksCount: Long,
    @SerialName("open_issues_count")
    val openIssuesCount: Long
)

@Serializable
data class Owner(
    @SerialName("avatar_url")
    val avatarUrl: String
)

@Serializable
data class SearchUsersResponse(
    @SerialName("items")
    val userList: List<UserItem>
)

@Serializable
data class UserItem(
    @SerialName("login")
    val userName: String,
    @SerialName("id")
    val userId: Long,
    @SerialName("url")
    val userUrl: String,
    @SerialName("avatar_url")
    val avatarUrl: String,
    @SerialName("followers_url")
    val followersUrl: String,
    @SerialName("following_url")
    val followingUrl: String,
    @SerialName("repos_url")
    val repositoryUrl: String
)