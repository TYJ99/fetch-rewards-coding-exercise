package com.tyj.fetchrewardscodingexercise.data.remote.dto

import com.tyj.fetchrewardscodingexercise.domain.model.Item

data class ItemDto(
    val id: Int,
    val listId: Int,
    val name: String?
)

fun ItemDto.toItem(): Item {
    return Item(
        id = id,
        listId = listId,
        name = name
    )
}
