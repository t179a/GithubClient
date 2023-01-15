package com.example.search.navigation

import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.search.ui.detail.UserDetailScreen
import com.example.search.ui.search.SearchRoute

const val searchNavigationRoute = "search_route"

fun NavController.navigateToSearch(navOptions: NavOptions? = null) {
    this.navigate(searchNavigationRoute, navOptions)
}

fun NavController.navigateToDetail(userName: String) {
    this.navigate("user_route/$userName")
}

fun NavGraphBuilder.searchScreen(
    onUserRowClick: (String) -> Unit,
) {
    composable(route = searchNavigationRoute) {
        SearchRoute(onUserClick = onUserRowClick)
    }
    composable(
        route = SearchNavGraph.userDetailRoute("{userName}"),
        arguments = listOf(navArgument("userName"){
            type = NavType.StringType
        })
    ) {
        UserDetailScreen(modifier = Modifier, onClick = {}, viewModel = hiltViewModel())
    }
}

object SearchNavGraph {
    fun userDetailRoute(userName: String) = "user_route/$userName"
}