package com.tyj.fetchrewardscodingexercise.domain.use_case.util

import com.google.common.truth.Truth.assertThat
import com.tyj.fetchrewardscodingexercise.data.remote.dto.toItem
import com.tyj.fetchrewardscodingexercise.data.repository.FakeItemRepository
import com.tyj.fetchrewardscodingexercise.domain.model.Item
import kotlinx.coroutines.test.runTest

import org.junit.Before
import org.junit.Test

class NameNotBlankOrNullFilterTest {

    private lateinit var nameNotBlankOrNullFilter: NameNotBlankOrNullFilter
    private lateinit var fakeItemRepository: FakeItemRepository
    private lateinit var items: List<Item>
    @Before
    fun setUp() {
        fakeItemRepository = FakeItemRepository(isSorter = false)
        nameNotBlankOrNullFilter = NameNotBlankOrNullFilter()
        runTest {
            items = fakeItemRepository.getItems().map { it.toItem() }
        }
    }

    @Test
    fun `Filter out any items where name is blank or null`() {
        val filteredItems = nameNotBlankOrNullFilter.filterItems(items)
        val gtItems = mutableListOf<Item>()
        for(item in items) {
            if(!item.name.isNullOrBlank()) gtItems.add(item)
        }
        println("Items        : $items")
        println("gtItems      : $gtItems")
        println("filteredItems: $filteredItems")

        assertThat(filteredItems).isEqualTo(gtItems)

        for (i in filteredItems.indices) {
            val current = filteredItems[i]

            // Ensure that name is not blank or null
            assertThat(current.name).isNotEmpty()
            assertThat(current.name).isNotNull()
        }
    }
}