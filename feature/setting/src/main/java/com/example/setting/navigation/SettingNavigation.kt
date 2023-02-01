package com.example.setting.navigation

import android.content.SharedPreferences
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.example.setting.ui.account.AccountScreen
import com.example.setting.ui.login.LoginScreen
import com.example.setting.ui.progress.ProgressScreen

private const val LOGIN_NAVIGATION_ROUTE = "setting_route"
private const val PROGRESS_NAVIGATION_ROUTE = "progress_route"
private const val ACCOUNT_NAVIGATION_ROUTE = "account_route"
private const val CALLBACK_URI = "org.example.android.t179a://callback"
fun NavController.navigateToAccount(encryptedPref: SharedPreferences) {
    val accessToken = encryptedPref.getString("access_token", "")
    if (accessToken!!.isEmpty()) {
        this.navigate(LOGIN_NAVIGATION_ROUTE)
    } else {
        this.navigate(ACCOUNT_NAVIGATION_ROUTE)
    }
}

fun NavGraphBuilder.settingScreen(
    navigateToLoginScreen: () -> Unit,
    navigateToAccountScreen: () -> Unit
) {
    composable(route = ACCOUNT_NAVIGATION_ROUTE) {
        AccountScreen(navigateToLoginScreen = navigateToLoginScreen)
    }
    composable(route = LOGIN_NAVIGATION_ROUTE) {
        LoginScreen(modifier = Modifier.fillMaxSize())
    }
    composable(
        route = PROGRESS_NAVIGATION_ROUTE,
        deepLinks = listOf(navDeepLink { uriPattern = CALLBACK_URI })
    ) {
        ProgressScreen(modifier = Modifier.fillMaxSize(), navigateToAccountScreen = navigateToAccountScreen)
    }
}
