package com.tyj.fetchrewardscodingexercise.presentation.navigation

sealed class Screen(val route: String){
    data object Home: Screen("home_screen")
    data object GroupedItems: Screen("grouped_items_screen")
}