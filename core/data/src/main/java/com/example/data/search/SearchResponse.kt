package com.example.data.search

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class SearchResponse(
    @SerialName("items")
    val RepositoryList: List<RepositoryItem>
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