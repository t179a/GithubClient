package com.example.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.data.search.UserItem
import com.example.search.ui.UserCardRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailScreen(
    modifier: Modifier = Modifier,
    onClick: (Long) -> Unit,
    viewModel: UserDetailViewModel
) {
    val uiState: UserDetailUiState by viewModel.userDetailState.collectAsState()
    Scaffold(modifier = modifier) { innerPadding ->
        if(uiState.isLoading) {
            LoadingBody(modifier.padding(innerPadding))
        } else{
            UserDetailBody(modifier = Modifier.padding(innerPadding), onClick = onClick, userList = uiState.userList)
        }

    }
}

@Composable
private fun LoadingBody(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(modifier = Modifier)
    }
}

@Composable
fun UserDetailBody(
    modifier: Modifier = Modifier, onClick: (Long) -> Unit, userList: List<UserItem>
) {
    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        Row(modifier = Modifier.padding(start = 24.dp)) {
            Text(text = "follower", color = MaterialTheme.colorScheme.primary,style = MaterialTheme.typography.headlineMedium)
        }
        UserCardRow(users = userList, onUserCardClick = onClick)
        Row(modifier = Modifier.padding(start = 24.dp)) {
            Text(text = "following", color = MaterialTheme.colorScheme.primary,style = MaterialTheme.typography.headlineMedium)
        }
        UserCardRow(users = userList, onUserCardClick = onClick)
    }
}
