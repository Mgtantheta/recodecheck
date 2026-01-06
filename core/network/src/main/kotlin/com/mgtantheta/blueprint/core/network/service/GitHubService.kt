package com.mgtantheta.blueprint.core.network.service

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.serialization.Serializable
import javax.inject.Inject
import javax.inject.Singleton

@Serializable
data class GitHubRepo(
    val name: String,
)

@Singleton
class GitHubService @Inject constructor(
    private val httpClient: HttpClient,
) {
    suspend fun getRepos(username: String): List<GitHubRepo> =
        httpClient.get("https://api.github.com/users/$username/repos").body()
}