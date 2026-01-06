package com.mgtantheta.blueprint.core.data.repository

import com.mgtantheta.blueprint.core.network.service.GitHubService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SampleRepositoryImpl @Inject constructor(
    private val gitHubService: GitHubService,
) : SampleRepository {

    private val _samples = MutableStateFlow<List<String>>(emptyList())

    override fun getSamples(): Flow<List<String>> = _samples.asStateFlow()

    override suspend fun refreshSamples() {
        val repos = gitHubService.getRepos("mgtantheta")
        _samples.value = repos.map { it.name }
    }
}
