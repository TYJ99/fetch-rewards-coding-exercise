package com.tyj.fetchrewardscodingexercise.di

import com.tyj.fetchrewardscodingexercise.data.remote.FetchHiringApi
import com.tyj.fetchrewardscodingexercise.data.repository.ItemRepositoryImpl
import com.tyj.fetchrewardscodingexercise.domain.repository.ItemRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideItemRepository(api: FetchHiringApi): ItemRepository {
        return ItemRepositoryImpl(api)
    }
}