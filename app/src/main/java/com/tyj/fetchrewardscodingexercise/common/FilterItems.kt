package com.tyj.fetchrewardscodingexercise.common

import com.tyj.fetchrewardscodingexercise.domain.model.Item

interface FilterItems {
    fun filterItems(items: List<Item>): List<Item>
}