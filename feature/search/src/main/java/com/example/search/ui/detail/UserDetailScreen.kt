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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.data.search.GithubRepositoryItem
import com.example.data.search.GithubUserItem
import com.example.search.ui.EmptyCardRow
import com.example.search.ui.RepositoryCardRow
import com.example.search.ui.UserCardRow
import com.example.search.ui.UserImage
import com.example.testing.data.githubRepositoryItemTestData
import com.example.testing.data.githubUserItemTestData

@Composable
fun UserDetailRoute(
    viewModel: UserDetailViewModel,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val uiState by viewModel.userDetailUiState.collectAsState()
    UserDetailScreen(uiState = uiState, onClick = onClick, modifier = modifier)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailScreen(
    uiState: UserDetailUiState,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(modifier = modifier) { innerPadding ->
        when (uiState) {
            is UserDetailUiState.Loading -> {
                LoadingBody(Modifier.padding(innerPadding))
            }
            is UserDetailUiState.UiState -> {
                UserDetailBody(
                    modifier = Modifier.padding(innerPadding),
                    onClick = onClick,
                    userItem = uiState.userItem,
                    followingList = uiState.followingList,
                    followersList = uiState.followersList,
                    repositoryList = uiState.repositoryList
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
        modifier = modifier
            .testTag("circular_progress_indicator")
            .fillMaxSize(),
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
        UserImageAndName(userItem = userItem)
        UserListRow(
            titleName = "Following",
            textForEmptyCase = "no following",
            userList = followingList,
            onClick = onClick,
            modifier = Modifier.testTag("following_list_row")
        )
        Spacer(modifier = Modifier.padding(12.dp))
        UserListRow(
            titleName = "Followers",
            textForEmptyCase = "no followers",
            userList = followersList,
            onClick = onClick,
            modifier = Modifier.testTag("followers_list_row")
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
                RepositoryCardRow(
                    users = repositoryList,
                    modifier = Modifier.testTag("repository_card_row")
                )
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

@Composable
private fun UserImageAndName(userItem: GithubUserItem, modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        UserImage(
            imageUrl = userItem.avatarUrl,
            modifier = Modifier
                .testTag("user_image")
                .size(300.dp)
                .align(CenterHorizontally),
            shape = RoundedCornerShape(30.dp)
        )
        Spacer(modifier = Modifier.padding(12.dp))
        Text(
            text = userItem.userName,
            modifier = Modifier
                .testTag("user_name")
                .align(CenterHorizontally)
                .padding(8.dp),
            style = MaterialTheme.typography.headlineLarge
        )
    }
}

@Preview
@Composable
private fun UserImageAndNamePreview() {
    UserImageAndName(userItem = githubUserItemTestData[0])
}

@Preview
@Composable
private fun UserDetailScreenPreview() {
    UserDetailScreen(
        uiState = UserDetailUiState.UiState(
            userItem = githubUserItemTestData[0],
            followersList = githubUserItemTestData,
            followingList = githubUserItemTestData,
            repositoryList = githubRepositoryItemTestData
        ),
        onClick = {}
    )
}

@Preview
@Composable
private fun UserListRowPreview() {
    UserListRow(
        titleName = "follower",
        textForEmptyCase = "no following",
        userList = githubUserItemTestData,
        onClick = {}
    )
}
