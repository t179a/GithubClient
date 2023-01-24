package com.example.setting.ui.account

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

@Composable
fun AccountScreen(
    modifier: Modifier = Modifier, navigateToLoginScreen: () -> Unit
) {
    val context = LocalContext.current
    val pref = context.getSharedPreferences("com.example.githubclient", Context.MODE_PRIVATE)

    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Account Screen")
            Button(onClick = {
                pref.edit().remove("accessToken").apply()
                navigateToLoginScreen()
            }) {
                Text("Logout")
            }
        }
    }
}