package com.tyj.fetchrewardscodingexercise.presentation.screens.items.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.tyj.fetchrewardscodingexercise.domain.model.Item
import com.tyj.fetchrewardscodingexercise.presentation.screens.items.ItemsState
import com.tyj.fetchrewardscodingexercise.presentation.navigation.Screen

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemsContent(
    itemsState: ItemsState,
    groupedItemMap: Map<Int, List<Item>>,
    modifier: Modifier = Modifier,
    certainListId: Int?,
    navController: NavController
) {
    Box(modifier = modifier.fillMaxSize()) {
        if(groupedItemMap.isNotEmpty()) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                if(certainListId != null) {
                    stickyHeader { ListIdHeader(listId = null, onClick = null) }
                    items(groupedItemMap[certainListId]!!, key = { item -> item.id}) { item ->
                        SingleItem(
                            item = item,
                        )
                    }
                }
                else {
                    groupedItemMap.forEach { (listId, groupedItems) ->
                        stickyHeader { ListIdHeader(
                            listId = listId,
                            onClick = {
                                navController.navigate("${Screen.GroupedItems.route}/${listId}")
                            }
                        ) }
                        items(groupedItems, key = {item -> item.id}) { item ->
                            SingleItem(
                                item = item,
                            )
                        }
                    }
                }
            }
        }

        if(itemsState.error.isNotBlank()) {
            ErrorText(
                errorMsg = itemsState.error,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        if(itemsState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}