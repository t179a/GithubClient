package com.example.search.navigation

import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.search.ui.detail.UserDetailScreen
import com.example.search.ui.search.SearchRoute

const val SEARCH_NAVIGATION_ROUTE = "search_route"

fun NavController.navigateToSearch() {
    this.navigate(SEARCH_NAVIGATION_ROUTE)
}

fun NavController.navigateToDetail(userName: String) {
    this.navigate("user_route/$userName")
}

fun NavGraphBuilder.searchScreen(
    onUserRowClick: (String) -> Unit,
    onUserCardClick: (String) -> Unit
) {
    composable(route = SEARCH_NAVIGATION_ROUTE) {
        SearchRoute(onClickForDetail = onUserRowClick)
    }
    composable(
        route = SearchNavGraph.userDetailRoute("{userName}"),
        arguments = listOf(
            navArgument("userName") {
                type = NavType.StringType
            }
        )
    ) {
        UserDetailScreen(
            modifier = Modifier,
            onClick = onUserCardClick,
            viewModel = hiltViewModel()
        )
    }
}

object SearchNavGraph {
    fun userDetailRoute(userName: String) = "user_route/$userName"
}
