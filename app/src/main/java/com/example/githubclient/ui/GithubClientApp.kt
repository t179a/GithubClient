package com.example.githubclient.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.githubclient.navigation.GithubClientNavHost

@Composable
fun GithubClientApp() {
   GithubClientNavHost(navController = rememberNavController() )
}