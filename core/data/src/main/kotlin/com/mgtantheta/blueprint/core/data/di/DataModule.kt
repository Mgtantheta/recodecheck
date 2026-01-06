package com.mgtantheta.blueprint.core.data.di

import com.mgtantheta.blueprint.core.data.repository.SampleRepository
import com.mgtantheta.blueprint.core.data.repository.SampleRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    abstract fun bindSampleRepository(
        impl: SampleRepositoryImpl,
    ): SampleRepository
}
