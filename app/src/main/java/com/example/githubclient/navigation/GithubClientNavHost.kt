package com.example.githubclient.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.favorite.navigation.favoriteScreen
import com.example.search.navigation.SEARCH_NAVIGATION_ROUTE
import com.example.search.navigation.navigateToDetail
import com.example.search.navigation.searchScreen
import com.example.setting.navigation.navigateToAccount
import com.example.setting.navigation.settingScreen

@Composable
fun GithubClientNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = SEARCH_NAVIGATION_ROUTE
) {
    val context = LocalContext.current
    NavHost(navController = navController, startDestination = startDestination, modifier = modifier) {
        searchScreen(
            onUserRowClick = { userName -> navController.navigateToDetail(userName) },
            onUserCardClick = { userName -> navController.navigateToDetail(userName = userName) }
        )
        favoriteScreen()
        settingScreen(
            navigateToLoginScreen = { navController.navigateToAccount(context) },
            navigateToAccountScreen = { navController.navigateToAccount(context) }
        )
    }
}
