package com.example.data.search

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class GithubNetworkService(val httpClient: HttpClient) {
    suspend inline fun <reified T : Any> get(
        url: String,
        query: String = "",
        sort: String = "indexed",
        order: String = "desc",
        perPage: Int = 30,
        page: Int = 1
    ): T = try {
        httpClient.get(url) {
            headers {
                append("accept", "application/vnd.github+json")
            }
            url {
                if(query.isNotEmpty()){
                    parameters.append("q", query)

                }
                parameters.append("sort", sort)
                parameters.append("order", order)
                parameters.append("per_page", perPage.toString())
                parameters.append("page", page.toString())
            }
        }.body()
    } catch (e: Throwable) {
        throw e
    }
}