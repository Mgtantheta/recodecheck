package com.mgtantheta.blueprint.core.network.service

import com.mgtantheta.blueprint.core.model.Repo
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.serialization.Serializable
import javax.inject.Inject
import javax.inject.Singleton

@Serializable
private data class GitHubRepoResponse(
    val name: String,
) {
    fun toDomain(): Repo = Repo(name = name)
}

@Singleton
class GitHubService
    @Inject
    constructor(
        private val httpClient: HttpClient,
    ) {
        suspend fun getRepos(username: String): List<Repo> =
            httpClient.get("https://api.github.com/users/$username/repos")
                .body<List<GitHubRepoResponse>>()
                .map { it.toDomain() }
    }
