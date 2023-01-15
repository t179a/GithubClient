package com.example.search.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.data.search.GithubUserItem
import com.example.search.R

@Composable
fun SearchRoute(
    modifier: Modifier = Modifier,
    onUserClick: (String) -> Unit,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val uiState by viewModel.searchUiState.collectAsState()
    SearchScreen(
        modifier = modifier,
        onSearch = { viewModel.onSearchUsers(it) },
        onWordChange = { viewModel.onUpdateSearchWord(it) },
        onUserRowClick = onUserClick,
        uiState = uiState
    )
}

//TODO importで「*」を使っている点を修正
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit,
    onWordChange: (String) -> Unit,
    onUserRowClick: (String) -> Unit,
    uiState: SearchUiState
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            SearchTextFieldAppBar(
                onSearch = onSearch,
                onWordChange = onWordChange,
                searchWord = uiState.searchWord,
            )
        },
    ) { innerPadding ->
        if (uiState.isLoading) {
            LoadingBody(modifier = Modifier.padding(innerPadding))
        } else {
            SearchedResultListField(
                modifier = Modifier.padding(innerPadding),
                onUserRowClick = onUserRowClick,
                userList = uiState.userList,
            )
        }

    }
}

@Composable
private fun SearchTextFieldAppBar(
    onSearch: (String) -> Unit,
    onWordChange: (String) -> Unit,
    searchWord: String,
    modifier: Modifier = Modifier,
) {
    SearchTextField(
        onSearch = onSearch,
        word = searchWord,
        onWordChange = onWordChange,
        modifier = modifier
    )

}

//TODO keyboardが自動で開かない点を修正
@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
private fun SearchTextField(
    onSearch: (String) -> Unit,
    word: String,
    onWordChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    TextField(
        value = word,
        onValueChange = onWordChange,
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.surfaceVariant)
            .focusRequester(focusRequester = focusRequester)
            .fillMaxWidth()
            .height(60.dp),
        placeholder = { Text(text = "検索する") },
        trailingIcon = {
            IconButton(
                onClick = { onWordChange("") }
            ) {
                Icon(
                    painter = rememberVectorPainter(image = Icons.Default.Clear),
                    contentDescription = "clear word",
                )
            }
        },
        keyboardActions = KeyboardActions(onDone = {
            onSearch(word)
            keyboardController?.hide()
        }),
        singleLine = true,
    )

}


@Composable
private fun SearchedResultListField(
    modifier: Modifier = Modifier,
    onUserRowClick: (String) -> Unit,
    userList: List<GithubUserItem>
) {
    LazyColumn(modifier = modifier) {
        items(items = userList, key = { userItem -> userItem.userId }) {
            GithubUserRow(
                userName = it.userName,
                userIconUrl = it.avatarUrl,
                onClick = {onUserRowClick(it.userName)}
            )
            Divider()

        }
    }
}

@Composable
private fun GithubUserRow(
    modifier: Modifier = Modifier,
    userName: String,
    userIconUrl: String,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(4.dp)
    ) {
        AsyncImage(
            model = userIconUrl, contentDescription = "user_icon", modifier = Modifier
                .clip(
                    CircleShape
                )
                .size(48.dp), placeholder = painterResource(id = R.drawable.ic_android_black_24dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = userName,
            fontSize = 16.sp,
            textAlign = TextAlign.Left,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .fillMaxWidth()
        )

    }

}

@Composable
private fun LoadingBody(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(modifier = Modifier)
    }
}

val fakeRepositoryList =
    List(20) {
        GithubUserItem(
            userName = "android",
            userId = it.toLong(),
            userUrl = "https://api.github.com/users/torvalds",
            avatarUrl = "https://avatars.githubusercontent.com/u/1024025?v=4",
            followersUrl = "https://api.github.com/users/torvalds/followers",
            followingUrl = "https://api.github.com/users/torvalds/following{/other_user}",
            repositoryUrl = "https://api.github.com/users/torvalds/repos",
        )
    }


@Preview
@Composable
private fun SearchScreenPreview() {
    SearchScreen(
        onSearch = {},
        onWordChange = {},
        onUserRowClick = {},
        uiState = SearchUiState(
            isLoading = false,
            isError = false,
            userList = fakeRepositoryList
        )
    )
}

@Preview
@Composable
private fun GithubRepositoryRowPreview() {
    GithubUserRow(
        userName = "",
        modifier = Modifier.fillMaxWidth(),
        onClick = {},
        userIconUrl = "https://avatars.githubusercontent.com/u/1024025?v=4"
    )
}

@Preview(widthDp = 390, heightDp = 400)
@Composable
private fun SearchedResultListFieldPreview() {
    SearchedResultListField(onUserRowClick = {},userList = fakeRepositoryList)
}

@Preview
@Composable
private fun SearchTextFieldAppBarPreview() {
    SearchTextFieldAppBar(onSearch = {}, onWordChange = {}, searchWord = "")
}

@Preview
@Composable
private fun SearchTextFieldPreview() {
    SearchTextField(onSearch = {}, word = "", onWordChange = {}, modifier = Modifier)
}
