package com.mgtantheta.blueprint.core.data.repository

import com.mgtantheta.blueprint.core.model.Repo
import com.mgtantheta.blueprint.core.network.service.GitHubService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SampleRepositoryImpl
    @Inject
    constructor(
        private val gitHubService: GitHubService,
    ) : SampleRepository {
        private val _repos = MutableStateFlow<List<Repo>>(emptyList())

        override fun getRepos(): Flow<List<Repo>> = _repos.asStateFlow()

        override suspend fun refresh() {
            _repos.value = gitHubService.getRepos("mgtantheta")
        }
    }
