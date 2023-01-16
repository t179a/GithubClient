package com.example.data.search

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class SearchRepositoriesResponse(
    @SerialName("items")
    val repositoryList: List<GithubRepositoryItem>
)

@Serializable
data class GithubRepositoryItem(
    val id: Long,
    @SerialName("full_name")
    val name: String,
    val owner: RepositoryOwner,
    val language: String?,
    @SerialName("stargazers_count")
    val stargazersCount: Long,
    @SerialName("watchers_count")
    val watchersCount: Long,
    @SerialName("forks_count")
    val forksCount: Long,
    @SerialName("open_issues_count")
    val openIssuesCount: Long,
    val description: String?
)

@Serializable
data class RepositoryOwner(
    @SerialName("avatar_url")
    val avatarUrl: String
)

@Serializable
data class SearchUsersResponse(
    @SerialName("items")
    val userList: List<GithubUserItem>
)

@Serializable
data class GithubUserItem(
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