package com.example.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.search.Owner
import com.example.data.search.RepositoryItem

//TODO importで「*」を使っている点を修正
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit,
    onWordChange: (String) -> Unit,
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
                repositoryList = uiState.repositoryList,
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
    repositoryList: List<RepositoryItem>
) {
    LazyColumn(modifier = modifier) {
        items(items = repositoryList, key = { repositoryItem -> repositoryItem.id }) {
            GithubRepositoryRow(
                fullName = it.name,
                stargazersCount = it.stargazersCount,
                language = it.language,
                onClick = {}
            )
            Divider()

        }
    }
}

@Composable
private fun GithubRepositoryRow(
    modifier: Modifier = Modifier,
    fullName: String,
    stargazersCount: Long,
    language: String?,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(4.dp)
    ) {
        Text(
            text = fullName,
            fontSize = 16.sp,
            textAlign = TextAlign.Left,
            modifier = Modifier.fillMaxWidth()
        )
        Row {
            Icon(
                painter = rememberVectorPainter(image = Icons.Default.Star),
                contentDescription = "star icon",
                modifier = Modifier.height(16.dp)
            )
            Text(text = stargazersCount.toString(), fontSize = 12.sp)
            Spacer(modifier = Modifier.width(12.dp))
            if (language != null) {
                Text(text = language, fontSize = 12.sp)
            }
        }
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
        RepositoryItem(
            id = it.toLong(),
            name = "t179a",
            owner = Owner(avatarUrl = "https://github.com/t179a.png"),
            language = "Kotlin",
            stargazersCount = 1000,
            watchersCount = 1000,
            forksCount = 1000,
            openIssuesCount = 1000
        )
    }

@Preview
@Composable
private fun LoadingBodyPreview() {
    LoadingBody()
}

@Preview
@Composable
private fun SearchScreenPreview() {
    SearchScreen(
        onSearch = {},
        onWordChange = {},
        uiState = SearchUiState(
            isLoading = false,
            isError = false,
            repositoryList = fakeRepositoryList
        )
    )
}

@Preview
@Composable
private fun GithubRepositoryRowPreview() {
    GithubRepositoryRow(
        fullName = "JetBrains/kotlin",
        stargazersCount = 43500,
        language = "Kotlin",
        modifier = Modifier.fillMaxWidth(),
        onClick = {}
    )
}

@Preview(widthDp = 390, heightDp = 400)
@Composable
private fun SearchedResultListFieldPreview() {
    SearchedResultListField(repositoryList = fakeRepositoryList)
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