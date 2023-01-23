package com.example.setting.ui.progress

import android.app.Activity
import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ProgressScreen(
    modifier: Modifier,
    viewModel: ProgressViewModel = hiltViewModel(),
    navigateToAccountScreen: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    val activity = context as Activity
    val callbackIntent = activity.intent
    val callbackCode = callbackIntent?.data?.getQueryParameter("code")
    LaunchedEffect(key1 = callbackCode) {
        viewModel.getAccessToken(callbackCode!!)
    }

    if (uiState.accessToken.isEmpty()) {
        Box(modifier = modifier, contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        LaunchedEffect(key1 = uiState.accessToken) {
            activity.getSharedPreferences("githubSetting", Context.MODE_PRIVATE)?.edit()?.apply{
                putString("accessToken", uiState.accessToken)
                apply()
            }
            navigateToAccountScreen()
        }
    }
}