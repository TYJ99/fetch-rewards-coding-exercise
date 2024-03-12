package com.tyj.fetchrewardscodingexercise.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tyj.fetchrewardscodingexercise.common.Constants.PARAM_LIST_ID
import com.tyj.fetchrewardscodingexercise.presentation.screens.items.ItemsScreen
import com.tyj.fetchrewardscodingexercise.presentation.screens.items.ItemsViewModel

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    itemsViewModel: ItemsViewModel = hiltViewModel(),
) {
    val itemsState by itemsViewModel.itemsState.collectAsStateWithLifecycle()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
    ) {
        composable(route = Screen.Home.route){ _ ->
            ItemsScreen(
                itemsState = itemsState,
                certainListId = null,
                navController = navController
            )
        }

        composable(
            route = "${Screen.GroupedItems.route}/{$PARAM_LIST_ID}"
        ) {navBackStackEntry ->
            val certainListId = (navBackStackEntry.arguments?.getString(PARAM_LIST_ID) ?: "1").toInt()
            ItemsScreen(
                itemsState = itemsState,
                certainListId = certainListId,
                navController = navController
            )
        }

    }
}