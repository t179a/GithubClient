package com.example.githubclient.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.search.navigation.navigateToDetail
import com.example.search.navigation.searchNavigationRoute
import com.example.search.navigation.searchScreen

@Composable
fun GithubClientNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = searchNavigationRoute
) {
    NavHost(navController = navController, startDestination = startDestination, modifier = modifier) {
        searchScreen(onUserRowClick = { userName -> navController.navigateToDetail(userName) }, onUserCardClick = {userName -> navController.navigateToDetail(userName = userName )})
    }
}