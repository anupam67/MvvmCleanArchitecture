package com.example.mvvmcleanarchitecture.di

import com.example.mvvmcleanarchitecture.data.PostApiService
import com.example.mvvmcleanarchitecture.data.PostRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun providePostRepository(postApiService: PostApiService): PostRepository {
        return PostRepository(postApiService)
    }
}