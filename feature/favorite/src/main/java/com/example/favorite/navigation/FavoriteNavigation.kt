package com.example.favorite.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.favorite.ui.FavoriteScreen


const val favoriteNavigationRoute = "favorite_route"
fun NavController.navigateToFavorite() {
    this.navigate(favoriteNavigationRoute)
}

fun NavGraphBuilder.favoriteScreen(
) {
    composable(route = favoriteNavigationRoute) {
        FavoriteScreen(modifier = Modifier.fillMaxSize())
    }
}