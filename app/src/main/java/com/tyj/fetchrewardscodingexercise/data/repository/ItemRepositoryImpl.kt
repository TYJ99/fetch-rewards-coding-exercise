package com.tyj.fetchrewardscodingexercise.data.repository

import com.tyj.fetchrewardscodingexercise.data.remote.FetchHiringApi
import com.tyj.fetchrewardscodingexercise.data.remote.dto.ItemDto
import com.tyj.fetchrewardscodingexercise.domain.repository.ItemRepository
import javax.inject.Inject

class ItemRepositoryImpl @Inject constructor(
    private val api: FetchHiringApi
): ItemRepository {
    /**
     * get [ItemDto] through API.
     *
     * @return a list of [ItemDto]
     */
    override suspend fun getItems(): List<ItemDto> {
        return api.getItems()
    }
}