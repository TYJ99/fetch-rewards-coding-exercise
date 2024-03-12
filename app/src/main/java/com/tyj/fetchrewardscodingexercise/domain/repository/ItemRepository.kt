package com.tyj.fetchrewardscodingexercise.domain.repository

import com.tyj.fetchrewardscodingexercise.data.remote.dto.ItemDto

interface ItemRepository {
    suspend fun getItems(): List<ItemDto>
}