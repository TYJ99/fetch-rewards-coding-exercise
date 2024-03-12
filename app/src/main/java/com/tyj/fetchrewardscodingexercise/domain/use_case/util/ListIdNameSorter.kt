package com.tyj.fetchrewardscodingexercise.domain.use_case.util

import com.tyj.fetchrewardscodingexercise.common.SortItems
import com.tyj.fetchrewardscodingexercise.domain.model.Item
import javax.inject.Inject

// Sort the results first by "listId" then by "name" when displaying.
class ListIdNameSorter @Inject constructor(): SortItems {
    override fun sortItems(items: List<Item>): List<Item> {
        return items.sortedWith(compareBy({ it.listId }, { it.name }))
    }
}