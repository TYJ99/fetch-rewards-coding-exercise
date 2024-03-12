package com.tyj.fetchrewardscodingexercise.presentation.screens.items

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.navigation.NavController
import com.tyj.fetchrewardscodingexercise.presentation.MainActivity
import com.tyj.fetchrewardscodingexercise.presentation.screens.items.components.ItemsContent
import com.tyj.fetchrewardscodingexercise.presentation.screens.items.components.TopBar

@Composable
fun ItemsScreen(
    itemsState: ItemsState,
    certainListId: Int?,
    navController: NavController,
) {
    val activity = when(LocalLifecycleOwner.current) {
        is MainActivity -> LocalLifecycleOwner.current as MainActivity
        else -> {
            val context = LocalContext.current
            if (context is MainActivity) {
                context
            } else {
                throw IllegalStateException("LocalLifecycleOwner is not MainActivity")
            }
        }
    }

    val onBackPressed: () -> Unit = {
        //activity?.moveTaskToBack(true)
        activity.onBackPressedDispatcher.onBackPressed()
    }

    var title = "Home"
    if(certainListId != null) title = "List $certainListId"

    val itemList = itemsState.items
    val groupedItemMap = itemList.groupBy { it.listId }

    Scaffold(
        topBar = {
            TopBar(
                onBackPressed = onBackPressed,
                title = title,
                certainListId = certainListId ?: -1,
                sortedListIds = groupedItemMap.keys,
                navController = navController
            )
        },
        content = {
            ItemsContent(
                itemsState = itemsState,
                groupedItemMap = groupedItemMap,
                modifier = Modifier.padding(it),
                certainListId = certainListId,
                navController = navController,
            )
        },
    )
}