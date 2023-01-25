package com.example.favorite.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.favorite.FavoriteScreen


const val favoriteNavigationRoute = "favorite_route"
fun NavController.navigateToFavorite() {
    this.navigate(favoriteNavigationRoute)
}

fun NavGraphBuilder.favoriteScreen(
) {
    composable(route = favoriteNavigationRoute) {
        FavoriteScreen()
    }
}