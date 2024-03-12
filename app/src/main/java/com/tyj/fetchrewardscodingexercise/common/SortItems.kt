package com.tyj.fetchrewardscodingexercise.common

import com.tyj.fetchrewardscodingexercise.domain.model.Item

interface SortItems {
    fun sortItems(items: List<Item>): List<Item>
}