package com.example.search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.search.SearchRoute
import com.example.search.SearchScreen

const val searchNavigationRoute = "search_route"

fun NavController.navigateToSearch(navOptions: NavOptions? = null) {
    this.navigate(searchNavigationRoute, navOptions)
}

fun NavGraphBuilder.searchScreen() {
    composable(route = searchNavigationRoute) {
        SearchRoute()
    }
}