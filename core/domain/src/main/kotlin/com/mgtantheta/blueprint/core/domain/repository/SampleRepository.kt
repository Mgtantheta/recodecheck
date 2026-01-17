package com.mgtantheta.blueprint.core.domain.repository

import com.mgtantheta.blueprint.core.model.Repo
import com.mgtantheta.blueprint.core.model.User
import kotlinx.coroutines.flow.Flow

interface SampleRepository {
    fun getUser(): Flow<User?>

    fun getRepos(): Flow<List<Repo>>

    suspend fun refresh()
}
