package com.example.githubclient.ui

import android.content.SharedPreferences
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.favorite.navigation.navigateToFavorite
import com.example.githubclient.navigation.GithubClientNavHost
import com.example.search.navigation.navigateToSearch
import com.example.setting.navigation.navigateToAccount

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GithubClientApp(encryptedPref: SharedPreferences, modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    Scaffold(
        modifier = modifier,
        bottomBar = {
            GithubClientBottomBar(
                encryptedPref,
                navController = navController,
                modifier = Modifier.testTag("BottomBar")
            )
        }
    ) { innerPadding ->
        GithubClientNavHost(
            encryptedPref = encryptedPref,
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun GithubClientBottomBar(
    encryptedPref: SharedPreferences,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    BottomAppBar(modifier = modifier.fillMaxWidth()) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            BottomAppBarItem(
                iconImageVector = Icons.Default.Search,
                iconDescription = "search",
                onClick = { navController.navigateToSearch() }
            )
            BottomAppBarItem(
                iconImageVector = Icons.Default.Favorite,
                iconDescription = "favorite",
                onClick = { navController.navigateToFavorite() }
            )
            BottomAppBarItem(
                iconImageVector = Icons.Default.AccountCircle,
                iconDescription = "account",
                onClick = { navController.navigateToAccount(encryptedPref = encryptedPref) }
            )
        }
    }
}

@Composable
fun BottomAppBarItem(
    iconImageVector: ImageVector,
    iconDescription: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = modifier.clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(imageVector = iconImageVector, contentDescription = iconDescription)
        Text(text = iconDescription)
    }
}
