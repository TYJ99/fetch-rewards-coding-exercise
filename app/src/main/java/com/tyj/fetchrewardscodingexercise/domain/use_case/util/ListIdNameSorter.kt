package com.tyj.fetchrewardscodingexercise.domain.use_case.util

import com.tyj.fetchrewardscodingexercise.common.SortItems
import com.tyj.fetchrewardscodingexercise.domain.model.Item
import javax.inject.Inject

class ListIdNameSorter @Inject constructor(): SortItems {
    /**
     * Sort the results first by "listId" then by "name" when displaying.
     *
     * @param items a list of [Item] you want to sort.
     *
     * @return a list of [Item] that have been sorted
     */
    override fun sortItems(items: List<Item>): List<Item> {
        return items.sortedWith(compareBy({ it.listId }, { it.name }))
    }
}