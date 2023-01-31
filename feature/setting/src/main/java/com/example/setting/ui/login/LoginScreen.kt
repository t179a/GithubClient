package com.example.setting.ui.login

import android.content.Context
import android.content.Intent
import android.net.Uri
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
import androidx.core.content.ContextCompat
import com.example.setting.BuildConfig
import java.util.UUID

private const val AUTHURL = "https://github.com/login/oauth/authorize"
private const val REDIRECTURL = "org.example.android.t179a://callback"

@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        val oAuthState: String = UUID.randomUUID().toString()
        context.getSharedPreferences("com.example.githubclient", Context.MODE_PRIVATE).edit().apply {
            putString("oAuthState", oAuthState)
            apply()
        }
        val uri =
            Uri.parse(AUTHURL).buildUpon().appendQueryParameter("client_id", BuildConfig.CLIENT_ID)
                .appendQueryParameter("redirect_uri", REDIRECTURL).appendQueryParameter("state", oAuthState).build()
        val loginIntent = Intent(Intent.ACTION_VIEW, uri)
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Login Screen")
            Button(onClick = {
                ContextCompat.startActivity(context, loginIntent, null)
            }) {
                Text(text = "Login")
            }
        }
    }
}
