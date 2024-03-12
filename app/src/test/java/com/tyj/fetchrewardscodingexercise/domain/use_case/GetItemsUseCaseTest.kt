package com.tyj.fetchrewardscodingexercise.domain.use_case

import com.google.common.truth.Truth.assertThat
import com.tyj.fetchrewardscodingexercise.common.NetworkResult
import com.tyj.fetchrewardscodingexercise.data.repository.FakeItemRepository
import com.tyj.fetchrewardscodingexercise.domain.use_case.util.ListIdNameSorter
import com.tyj.fetchrewardscodingexercise.domain.use_case.util.NameNotBlankOrNullFilter
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class GetItemsUseCaseTest {
    private lateinit var listIdNameSorter: ListIdNameSorter
    private lateinit var nameNotBlankOrNullFilter: NameNotBlankOrNullFilter
    private lateinit var fakeItemRepositoryNormal: FakeItemRepository
    private lateinit var fakeItemRepositoryHttpException: FakeItemRepository
    private lateinit var fakeItemRepositoryIOException: FakeItemRepository
    private lateinit var getItemsUseCaseNormal: GetItemsUseCase
    private lateinit var getItemsUseCaseHttpException: GetItemsUseCase
    private lateinit var getItemsUseCaseIOException: GetItemsUseCase
    @Before
    fun setUp() {
        fakeItemRepositoryNormal = FakeItemRepository(isSorter = true)
        fakeItemRepositoryHttpException = FakeItemRepository(isSorter = true, isHTTPException = true)
        fakeItemRepositoryIOException = FakeItemRepository(isSorter = true, isIOException = true)
        nameNotBlankOrNullFilter = NameNotBlankOrNullFilter()
        listIdNameSorter = ListIdNameSorter()
        getItemsUseCaseNormal = GetItemsUseCase(
            repository = fakeItemRepositoryNormal,
            sortItems = listIdNameSorter,
            filterItems = nameNotBlankOrNullFilter,
        )
        getItemsUseCaseHttpException = GetItemsUseCase(
            repository = fakeItemRepositoryHttpException,
            sortItems = listIdNameSorter,
            filterItems = nameNotBlankOrNullFilter,
        )
        getItemsUseCaseIOException = GetItemsUseCase(
            repository = fakeItemRepositoryIOException,
            sortItems = listIdNameSorter,
            filterItems = nameNotBlankOrNullFilter,
        )
    }

    @Test
    fun `Get items use case test`()  = runTest{
        var count = 0
        getItemsUseCaseNormal().collect {
            when (count) {
                0 -> {
                    assert(it is NetworkResult.Loading)
                }
                1 -> {
                    assert(it is NetworkResult.Success)
                    val sortedFilteredItems = it.data!!
                    // Verify that the results are sorted correctly
                    for (i in 0 until sortedFilteredItems.size - 1) {
                        val current = sortedFilteredItems[i]
                        val next = sortedFilteredItems[i + 1]

                        // Ensure that listId is sorted in ascending order
                        if (current.listId == next.listId) {
                            // If listId is the same, ensure that name is sorted in ascending order
                            assertThat(next.name).isAtLeast(current.name)
                        } else {
                            assertThat(next.listId).isAtLeast(current.listId)
                        }

                        // Ensure that name is not blank or null
                        assertThat(current.name).isNotEmpty()
                        assertThat(current.name).isNotNull()

                    }
                    // Ensure that the last name is not blank or null
                    assertThat(sortedFilteredItems.last().name).isNotEmpty()
                    assertThat(sortedFilteredItems.last().name).isNotNull()
                }
                else -> {
                    assert(count < 2)
                }
            }
            ++count
        }
        val responseBody = "HTTP error".toResponseBody("plain/text".toMediaTypeOrNull())
        val httpException = HttpException(Response.error<ResponseBody>(500, responseBody))
        count = 0
        getItemsUseCaseHttpException().collect {
            when (count) {
                0 -> assert(it is NetworkResult.Loading)
                1 -> {
                    println(it.message)
                    assert(it is NetworkResult.Error)
                    assert(it.message != null)
                    assert(it.message == httpException.localizedMessage || it.message == "Unexpected error")
                }
                else -> assert(count < 2)
            }
            ++count

        }
        count = 0
        getItemsUseCaseIOException().collect {
            when (count) {
                0 -> assert(it is NetworkResult.Loading)
                1 -> {
                    println(it.message)
                    assert(it is NetworkResult.Error)
                    assert(it.message != null)
                    assert(it.message == "Couldn't reach server. Check your internet connection. ${IOException().localizedMessage}")
                }
                else -> assert(count < 2)
            }
            ++count

        }


    }
}