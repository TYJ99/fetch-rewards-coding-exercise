package com.tyj.fetchrewardscodingexercise.data.remote

import com.tyj.fetchrewardscodingexercise.data.remote.dto.ItemDto
import retrofit2.http.GET

interface FetchHiringApi {
    @GET("/hiring.json")
    suspend fun getItems(): List<ItemDto>
}