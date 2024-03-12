package com.tyj.fetchrewardscodingexercise.domain.use_case

import com.tyj.fetchrewardscodingexercise.common.FilterItems
import com.tyj.fetchrewardscodingexercise.common.NetworkResult
import com.tyj.fetchrewardscodingexercise.common.SortItems
import com.tyj.fetchrewardscodingexercise.data.remote.dto.toItem
import com.tyj.fetchrewardscodingexercise.domain.model.Item
import com.tyj.fetchrewardscodingexercise.domain.repository.ItemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

// use case should only has one public function that is used to execute the use case
class GetItemsUseCase @Inject constructor(
    private val repository: ItemRepository,
    private val sortItems: SortItems,
    private val filterItems: FilterItems,
) {
    operator fun invoke(): Flow<NetworkResult<List<Item>>> = flow {
        try {
            emit(NetworkResult.Loading())
            val originalItems = repository.getItems().map{ it.toItem() }
            val newItems = filterItems.filterItems(sortItems.sortItems(originalItems))
            emit(NetworkResult.Success(newItems))
        }
        catch (e: HttpException) {
            emit(NetworkResult.Error(e.localizedMessage ?: "Unexpected error"))
        }
        catch (e: IOException) {
            emit(NetworkResult.Error("Couldn't reach server. Check your internet connection. ${e.localizedMessage}"))
        }
    }

}