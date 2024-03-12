package com.tyj.fetchrewardscodingexercise.data.repository

import com.tyj.fetchrewardscodingexercise.data.remote.dto.ItemDto
import com.tyj.fetchrewardscodingexercise.domain.repository.ItemRepository
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import kotlin.random.Random

class FakeItemRepository(
    private val isSorter: Boolean,
    private val isIOException: Boolean = false,
    private val isHTTPException: Boolean = false
): ItemRepository {

    private val items = mutableListOf<ItemDto>()

    init {
        repeat(200) {
            val id = Random.nextInt(1000)
            val listId = Random.nextInt(4) + 1
            val names = listOf("", null, "item $id")
            var name = names[Random.nextInt(3)]
            if(isSorter) name = "item $id"
            items.add(
                ItemDto(
                    id = id,
                    listId = listId,
                    name = name
                )
            )
        }
    }
    override suspend fun getItems(): List<ItemDto> {
        val responseBody = "HTTP error".toResponseBody("plain/text".toMediaTypeOrNull())
        if(isIOException) throw IOException()
        if(isHTTPException) throw HttpException(Response.error<ResponseBody>(500, responseBody))
        return items
    }
}