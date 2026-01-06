package com.mgtantheta.blueprint.core.data.repository

import com.mgtantheta.blueprint.core.model.Repo
import kotlinx.coroutines.flow.Flow

/**
 * サンプルRepository Interface
 * 実際のプロジェクトでは core:domain に移動する
 */
interface SampleRepository {
    fun getRepos(): Flow<List<Repo>>

    suspend fun refresh()
}
