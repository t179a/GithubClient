package com.example.data.search

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.plugins.ResponseException
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.util.cio.ChannelReadException
import kotlinx.coroutines.TimeoutCancellationException

class GithubNetworkService(val httpClient: HttpClient) {
    suspend inline fun <reified T : Any> get(
        url: String,
        accessToken: String = "",
        query: String = "",
        sort: String = "indexed",
        order: String = "desc",
        perPage: Int = 30,
        page: Int = 1
    ): T = try {
        httpClient.get(url) {
            headers {
                append("accept", "application/vnd.github+json")
                if (accessToken.isNotEmpty()) {
                    append("Authorization", "Bearer $accessToken")
                }
            }
            url {
                if (query.isNotEmpty()) {
                    parameters.append("q", query)
                }
                parameters.append("sort", sort)
                parameters.append("order", order)
                parameters.append("per_page", perPage.toString())
                parameters.append("page", page.toString())
            }
        }.body()
    } catch (e: Throwable) {
        Log.d("GithubNetworkServiceError", e.message.toString())
        throw e.toAppError()
    }

    suspend inline fun <reified T : Any> post(
        url: String,
        clientId: String,
        clientSecret: String,
        code: String
    ): T = try {
        httpClient.post(url) {
            headers {
                append("accept", "application/vnd.github+json")
            }
            url {
                parameters.append("client_id", clientId)
                parameters.append("client_secret", clientSecret)
                parameters.append("code", code)
            }
        }.body()
    } catch (e: Throwable) {
        Log.d("GithubNetworkServiceError", e.message.toString())
        throw e.toAppError()
    }
}

public fun Throwable.toAppError(): AppError {
    return when (this) {
        is AppError -> this
        is ResponseException ->
            return AppError.ApiException.ServerException(this)
        is ChannelReadException ->
            return AppError.ApiException.NetworkException(this)
        is TimeoutCancellationException,
        is HttpRequestTimeoutException,
        is SocketTimeoutException -> {
            AppError.ApiException
                .TimeoutException(this)
        }
        else -> AppError.UnknownException(this)
    }
}
