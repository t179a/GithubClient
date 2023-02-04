package com.example.testing.data

import com.example.data.search.GithubRepositoryItem
import com.example.data.search.RepositoryOwner

val githubRepositoryItemTestData: List<GithubRepositoryItem> =
    List(20) {
        GithubRepositoryItem(
            id =  576365772,
            name =  "Calculator",
            owner = RepositoryOwner(avatarUrl = "https://avatars.githubusercontent.com/u/67852630?v=4"),
            language = "Kotlin",
            stargazersCount = 0,
            watchersCount = 0,
            forksCount = 0,
            openIssuesCount = 2,
            description = "calculator(android app)"
        )
    }