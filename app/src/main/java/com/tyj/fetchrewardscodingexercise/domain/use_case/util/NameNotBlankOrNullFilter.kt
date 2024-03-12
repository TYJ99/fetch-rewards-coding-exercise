package com.tyj.fetchrewardscodingexercise.domain.use_case.util

import com.tyj.fetchrewardscodingexercise.common.FilterItems
import com.tyj.fetchrewardscodingexercise.domain.model.Item
import javax.inject.Inject

// Filter out any items where "name" is blank or null.
class NameNotBlankOrNullFilter @Inject constructor(): FilterItems {
    override fun filterItems(items: List<Item>): List<Item> {
        return items.filter { !it.name.isNullOrBlank() }
    }
}