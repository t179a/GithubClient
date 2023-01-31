package com.example.favorite.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.favorite.ui.FavoriteScreen

const val FAVORITE_NAVIGATION_ROUTE = "FAVORITE_ROUTE"
fun NavController.navigateToFavorite() {
    this.navigate(FAVORITE_NAVIGATION_ROUTE)
}

fun NavGraphBuilder.favoriteScreen() {
    composable(route = FAVORITE_NAVIGATION_ROUTE) {
        FavoriteScreen(modifier = Modifier.fillMaxSize())
    }
}
