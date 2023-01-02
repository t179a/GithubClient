package com.example.data.search

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class SearchNetworkService(val httpClient: HttpClient) {
    suspend inline fun <reified T : Any> get(
        url: String,
        query: String,
        sort: String = "indexed",
        order: String = "desc",
        perPage: Int = 30,
        page: Int = 1
    ): T = try {
        httpClient.get(url) {
            headers {
                append("q", query)
                append("sort", sort)
                append("order", order)
                append("per_page", perPage.toString())
                append("page", page.toString())
            }
        }.body()
    } catch (e: Throwable) {
        throw e
    }
}