package com.example.search.ui.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.data.search.GithubRepositoryItem
import com.example.data.search.GithubUserItem
import com.example.search.ui.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailScreen(
    modifier: Modifier = Modifier,
    onClick: (Long) -> Unit,
    viewModel: UserDetailViewModel
) {
    val uiState: UserDetailUiState by viewModel.userDetailUiState.collectAsState()
    Scaffold(modifier = modifier) { innerPadding ->
        when (uiState) {
            is UserDetailUiState.Loading -> {
                LoadingBody(modifier.padding(innerPadding))

            }
            is UserDetailUiState.UiState -> {
                UserDetailBody(
                    modifier = Modifier.padding(innerPadding),
                    onClick = onClick,
                    userItem = (uiState as UserDetailUiState.UiState).userItem,
                    followingList = (uiState as UserDetailUiState.UiState).followingList,
                    followersList = (uiState as UserDetailUiState.UiState).followersList,
                    repositoryList = (uiState as UserDetailUiState.UiState).repositoryList
                )
            }
        }
    }
}

@Composable
private fun LoadingBody(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Center
    ) {
        CircularProgressIndicator(modifier = Modifier)
    }
}

@Composable
private fun UserDetailBody(
    modifier: Modifier = Modifier,
    onClick: (Long) -> Unit,
    userItem: GithubUserItem,
    followingList: List<GithubUserItem>,
    followersList: List<GithubUserItem>,
    repositoryList: List<GithubRepositoryItem>
) {
    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        Spacer(modifier = Modifier.padding(12.dp))
        UserImage(
            imageUrl = userItem.avatarUrl, modifier = Modifier
                .size(300.dp)
                .align(CenterHorizontally), shape = RoundedCornerShape(30.dp)
        )
        Spacer(modifier = Modifier.padding(12.dp))
        Text(
            text = userItem.userName, modifier = Modifier
                .align(CenterHorizontally)
                .padding(8.dp), style = MaterialTheme.typography.headlineLarge
        )
        Spacer(modifier = Modifier.padding(12.dp))
        Row(modifier = Modifier.padding(start = 24.dp)) {
            Text(
                text = "follower",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.headlineMedium
            )
        }
        Box(
            modifier = Modifier
                .height(230.dp)
                .fillMaxWidth(), contentAlignment = Center
        ) {
            if (followersList.isEmpty()) {
                EmptyCardRow(modifier = Modifier.height(230.dp), text = "no followers")
            } else {
                UserCardRow(users = followersList, onUserCardClick = onClick)
            }
        }
        Spacer(modifier = Modifier.padding(12.dp))
        Row(modifier = Modifier.padding(start = 24.dp)) {
            Text(
                text = "following",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.headlineMedium
            )
        }
        Box(
            modifier = Modifier
                .height(230.dp)
                .fillMaxWidth(), contentAlignment = Center
        ) {
            if (followingList.isEmpty()) {
                EmptyCardRow(modifier = Modifier.height(230.dp), text = "no following")
            } else {
                UserCardRow(users = followingList, onUserCardClick = onClick)
            }
        }
        Spacer(modifier = Modifier.padding(12.dp))
        Row(modifier = Modifier.padding(start = 24.dp)) {
            Text(
                text = "Repository",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.headlineMedium
            )
        }
        Box(
            modifier = Modifier
                .height(160.dp)
                .fillMaxWidth(), contentAlignment = Center
        ) {
            if (repositoryList.isEmpty()) {
                EmptyCardRow(modifier = Modifier.height(160.dp), text = "no repository")
            } else {
                RepositoryCardRow(users = repositoryList)
            }
        }
        Spacer(modifier = Modifier.padding(12.dp))
    }
}

