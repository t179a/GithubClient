package com.example.search.ui.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.data.search.GithubRepositoryItem
import com.example.data.search.GithubUserItem
import com.example.search.ui.EmptyCardRow
import com.example.search.ui.RepositoryCardRow
import com.example.search.ui.UserCardRow
import com.example.search.ui.UserImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailScreen(
    viewModel: UserDetailViewModel,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val uiState: UserDetailUiState by viewModel.userDetailUiState.collectAsState()
    Scaffold(modifier = modifier) { innerPadding ->
        when (uiState) {
            is UserDetailUiState.Loading -> {
                LoadingBody(Modifier.padding(innerPadding))
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
    onClick: (String) -> Unit,
    userItem: GithubUserItem,
    followingList: List<GithubUserItem>,
    followersList: List<GithubUserItem>,
    repositoryList: List<GithubRepositoryItem>,
    modifier: Modifier = Modifier,

) {
    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        Spacer(modifier = Modifier.padding(12.dp))
        UserImage(
            imageUrl = userItem.avatarUrl,
            modifier = Modifier
                .size(300.dp)
                .align(CenterHorizontally),
            shape = RoundedCornerShape(30.dp)
        )
        Spacer(modifier = Modifier.padding(12.dp))
        Text(
            text = userItem.userName,
            modifier = Modifier
                .align(CenterHorizontally)
                .padding(8.dp),
            style = MaterialTheme.typography.headlineLarge
        )
        Spacer(modifier = Modifier.padding(12.dp))
        UserListRow(
            titleName = "Following",
            textForEmptyCase = "no following",
            userList = followingList,
            onClick = onClick
        )
        Spacer(modifier = Modifier.padding(12.dp))
        UserListRow(
            titleName = "Followers",
            textForEmptyCase = "no followers",
            userList = followersList,
            onClick = onClick
        )
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
                .height(230.dp)
                .fillMaxWidth(),
            contentAlignment = Center
        ) {
            if (repositoryList.isEmpty()) {
                EmptyCardRow(modifier = Modifier.height(230.dp), text = "no repository")
            } else {
                RepositoryCardRow(users = repositoryList)
            }
        }
        Spacer(modifier = Modifier.padding(12.dp))
    }
}

@Composable
private fun UserListRow(
    titleName: String,
    textForEmptyCase: String,
    userList: List<GithubUserItem>,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Row(modifier = Modifier.padding(start = 24.dp)) {
            Text(
                text = titleName,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.headlineMedium
            )
        }
        Box(
            modifier = Modifier
                .height(230.dp)
                .fillMaxWidth()
        ) {
            if (userList.isEmpty()) {
                EmptyCardRow(modifier = Modifier.height(230.dp), text = textForEmptyCase)
            } else {
                UserCardRow(users = userList, onUserCardClick = onClick)
            }
        }
    }
}
