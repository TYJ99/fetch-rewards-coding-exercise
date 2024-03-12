package com.tyj.fetchrewardscodingexercise.domain.use_case.util

import com.tyj.fetchrewardscodingexercise.common.FilterItems
import com.tyj.fetchrewardscodingexercise.domain.model.Item
import javax.inject.Inject

class NameNotBlankOrNullFilter @Inject constructor(): FilterItems {
    /**
     * Filter out any items where "name" is blank or null.
     *
     * @param items a list of [Item] you want to filter out.
     *
     * @return a list of [Item] that have been filtered out
     */
    override fun filterItems(items: List<Item>): List<Item> {
        return items.filter { !it.name.isNullOrBlank() }
    }
}