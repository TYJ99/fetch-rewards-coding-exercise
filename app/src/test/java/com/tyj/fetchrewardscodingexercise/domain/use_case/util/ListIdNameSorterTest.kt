package com.tyj.fetchrewardscodingexercise.domain.use_case.util

import com.google.common.truth.Truth.assertThat
import com.tyj.fetchrewardscodingexercise.data.remote.dto.toItem
import com.tyj.fetchrewardscodingexercise.data.repository.FakeItemRepository
import com.tyj.fetchrewardscodingexercise.domain.model.Item
import kotlinx.coroutines.test.runTest

import org.junit.Before
import org.junit.Test

class ListIdNameSorterTest {
    private lateinit var listIdNameSorter: ListIdNameSorter
    private lateinit var fakeItemRepository: FakeItemRepository
    private lateinit var items: List<Item>
    @Before
    fun setUp() {
        fakeItemRepository = FakeItemRepository(isSorter = true)
        listIdNameSorter = ListIdNameSorter()
        runTest {
            items = fakeItemRepository.getItems().map { it.toItem() }
        }
    }

    @Test
    fun `Sort the items first by listId then by name`() {
        val sortedItems = listIdNameSorter.sortItems(items)
        println("items: $items")

        // Verify that the results are sorted correctly
        for (i in 0 until sortedItems.size - 1) {
            val current = sortedItems[i]
            val next = sortedItems[i + 1]

            // Ensure that listId is sorted in ascending order
            if (current.listId == next.listId) {
                // If listId is the same, ensure that name is sorted in ascending order
                assertThat(next.name).isAtLeast(current.name)
            } else {
                assertThat(next.listId).isAtLeast(current.listId)
            }

        }

    }

}