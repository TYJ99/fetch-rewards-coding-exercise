package com.tyj.fetchrewardscodingexercise.di

import com.tyj.fetchrewardscodingexercise.common.FilterItems
import com.tyj.fetchrewardscodingexercise.domain.use_case.util.NameNotBlankOrNullFilter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class FilterModule {

    @Binds
    @Singleton
    abstract fun bindNameNotBlankOrNullFilter(filterItems: NameNotBlankOrNullFilter): FilterItems
}