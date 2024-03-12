package com.tyj.fetchrewardscodingexercise.presentation.screens.items

import com.tyj.fetchrewardscodingexercise.domain.model.Item

data class ItemsState(
    val isLoading: Boolean = false,
    val items: List<Item> = emptyList(),
    val error: String = ""
)

