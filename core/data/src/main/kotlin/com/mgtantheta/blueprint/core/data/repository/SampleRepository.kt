package com.mgtantheta.blueprint.core.data.repository

import kotlinx.coroutines.flow.Flow

/**
 * サンプルRepository Interface
 * 実際のプロジェクトでは core:domain に移動する
 */
interface SampleRepository {
    fun getSamples(): Flow<List<String>>

    suspend fun refreshSamples()
}
