package com.example.data.auth

import com.example.data.search.GithubNetworkService

class AuthApi(
    private val networkService: GithubNetworkService
) {
    suspend fun postCertificationInfo(clientId: String, clientSecret: String, code: String): AuthResponse {
        return networkService.post(url = "https://github.com/login/oauth/access_token",
        clientId = clientId, clientSecret = clientSecret, code = code)
    }
}