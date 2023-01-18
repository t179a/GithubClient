package com.example.setting.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.setting.ui.LoginScreen

const val settingNavigationRoute = "setting_route"
fun NavController.navigateToAccount() {
    this.navigate(settingNavigationRoute)
}

fun NavGraphBuilder.settingScreen(
) {
    composable(route = settingNavigationRoute) {
        LoginScreen(modifier = Modifier)
    }
}