package com.tyj.fetchrewardscodingexercise.presentation.screens.items

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tyj.fetchrewardscodingexercise.common.NetworkResult
import com.tyj.fetchrewardscodingexercise.domain.use_case.GetItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ItemsViewModel @Inject constructor(
    private val getItemsUseCase: GetItemsUseCase
): ViewModel() {
    private val _itemsState = MutableStateFlow(ItemsState())
    val itemsState = _itemsState.asStateFlow()

    init {
        getItems()
    }

    private fun getItems() {
        getItemsUseCase()
            .onEach { result ->
                when(result) {
                    is NetworkResult.Success -> {
                        _itemsState.value = ItemsState(items = result.data?: emptyList())
                    }
                    is NetworkResult.Error -> {
                        _itemsState.value = ItemsState(error = result.message?: "Unexpected error")
                    }
                    is NetworkResult.Loading -> {
                        _itemsState.value = ItemsState(isLoading = true)
                    }
                }
            }
            .launchIn(viewModelScope)
    }

}