package com.tyj.fetchrewardscodingexercise.di

import com.tyj.fetchrewardscodingexercise.common.SortItems
import com.tyj.fetchrewardscodingexercise.domain.use_case.util.ListIdNameSorter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SorterModule {
    @Binds
    @Singleton
    abstract fun bindListIdNameSorter(sortItems: ListIdNameSorter): SortItems
}