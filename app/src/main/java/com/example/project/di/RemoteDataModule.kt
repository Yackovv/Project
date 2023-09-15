package com.example.project.di

import com.example.data.repository.RemoteRepositoryImpl
import com.example.domain.repository.RemoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
interface RemoteDataModule {

    @Binds
    @ApplicationScope
    fun bindsRemoteRepository(impl: RemoteRepositoryImpl): RemoteRepository
}