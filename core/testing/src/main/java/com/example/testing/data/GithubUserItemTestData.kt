package com.example.testing.data

import com.example.data.search.GithubUserItem

val githubUserItemTestData: List<GithubUserItem> =
    List(20) {
        GithubUserItem(
            userName = "android",
            userId = it.toLong(),
            userUrl = "https://api.github.com/users/t179a",
            avatarUrl = "https://avatars.githubusercontent.com/u/67852630?v=4",
            followersUrl = "https://api.github.com/users/t179a/followers",
            followingUrl = "https://api.github.com/users/t179a/following{/other_user}",
            repositoryUrl = "https://api.github.com/users/t179a/repos",
        )
    }