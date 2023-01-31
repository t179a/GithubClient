package com.example.search.ui.search

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
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
    onClickForDetail: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val uiState by viewModel.searchUiState.collectAsState()
    SearchScreen(
        modifier = modifier,
        onSearch = viewModel::onSearchUsers,
        onWordChange = { viewModel.onUpdateSearchWord(it) },
        onClickForDetail = onClickForDetail,
        onClickForSave = { viewModel.onSaveUser(it) },
        uiState = uiState
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    onSearch: (String, String) -> Unit,
    onWordChange: (String) -> Unit,
    onClickForDetail: (String) -> Unit,
    onClickForSave: (GithubUserItem) -> Unit,
    uiState: SearchUiState,
    modifier: Modifier = Modifier
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
                onClickForDetail = onClickForDetail,
                userList = uiState.userList,
                onClickForSave = onClickForSave
            )
        }
    }
}

@Composable
private fun SearchTextFieldAppBar(
    onSearch: (String, String) -> Unit,
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

// TODO keyboardが自動で開かない点を修正
@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
private fun SearchTextField(
    onSearch: (String, String) -> Unit,
    word: String,
    onWordChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }
    val context = LocalContext.current
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
            val accessToken =
                context.getSharedPreferences("com.example.githubclient", Context.MODE_PRIVATE)
                    .getString("accessToken", "")
            onSearch(word, accessToken!!)
            keyboardController?.hide()
        }),
        singleLine = true,
    )
}

@Composable
private fun SearchedResultListField(
    userList: List<GithubUserItem>,
    onClickForDetail: (String) -> Unit,
    onClickForSave: (GithubUserItem) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(items = userList, key = { userItem -> userItem.userId }) {
            GithubUserRow(
                userName = it.userName,
                userIconUrl = it.avatarUrl,
                onClickForDetail = { onClickForDetail(it.userName) },
                onClickForSave = { onClickForSave(it) }
            )
            Divider()
        }
    }
}

@Composable
private fun GithubUserRow(
    userName: String,
    userIconUrl: String,
    onClickForDetail: () -> Unit,
    onClickForSave: () -> Unit,
    modifier: Modifier = Modifier

) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .clickable(onClick = onClickForDetail)
                .weight(5f)
                .padding(4.dp)
        ) {
            AsyncImage(
                model = userIconUrl,
                contentDescription = "user_icon",
                modifier = Modifier
                    .clip(
                        CircleShape
                    )
                    .size(48.dp),
                placeholder = painterResource(id = R.drawable.ic_android_black_24dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = userName,
                fontSize = 16.sp,
                textAlign = TextAlign.Left,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Image(
            imageVector = Icons.Default.FavoriteBorder,
            contentDescription = "favorite",
            modifier = Modifier
                .clickable(onClick = onClickForSave)
                .weight(1f)
                .padding(horizontal = 24.dp)
                .size(24.dp)
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

private val previewFun = { _: String, _: String -> }

@Preview
@Composable
private fun SearchScreenPreview() {
    SearchScreen(
        onSearch = previewFun,
        onWordChange = {},
        onClickForDetail = {},
        onClickForSave = {},
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
        onClickForDetail = {},
        onClickForSave = {},
        userIconUrl = "https://avatars.githubusercontent.com/u/1024025?v=4"
    )
}

@Preview
@Composable
private fun SearchTextFieldPreview() {
    SearchTextField(onSearch = previewFun, word = "", onWordChange = {}, modifier = Modifier)
}
