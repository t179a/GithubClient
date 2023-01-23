package com.example.setting.navigation

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.example.setting.ui.account.AccountScreen
import com.example.setting.ui.login.LoginScreen
import com.example.setting.ui.progress.ProgressScreen

private const val loginNavigationRoute = "setting_route"
private const val progressNavigationRoute = "progress_route"
private const val accountNavigationRoute = "account_route"
private const val uri = "org.example.android.t179a://callback"
fun NavController.navigateToAccount(context: Context) {
    val pref = context.getSharedPreferences("githubSetting", Context.MODE_PRIVATE)
    val accessToken = pref.getString("accessToken", "")
    if(accessToken!!.isEmpty()){
        this.navigate(loginNavigationRoute)
    }else{
        this.navigate(accountNavigationRoute)
    }

}

fun NavGraphBuilder.settingScreen(
    navigateToLoginScreen: () -> Unit,
    navigateToAccountScreen: () -> Unit
) {
    composable(route = accountNavigationRoute)  {
        AccountScreen(navigateToLoginScreen = navigateToLoginScreen)
    }
    composable(route = loginNavigationRoute) {
        LoginScreen(modifier = Modifier.fillMaxSize())
    }
    composable(
        route = progressNavigationRoute,
        deepLinks = listOf(navDeepLink { uriPattern = uri })
    ) {
        ProgressScreen(modifier = Modifier.fillMaxSize(), navigateToAccountScreen = navigateToAccountScreen)
    }
}