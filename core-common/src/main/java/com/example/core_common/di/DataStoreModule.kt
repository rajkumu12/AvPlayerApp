package com.example.core_common.di

import android.content.Context
import com.example.core_common.data.datastore.PreferenceManager
import com.example.core_module.repository.PreferenceRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindPreferenceRepository(
        preferenceManager: PreferenceManager
    ): PreferenceRepository
}