package com.example.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


//TODO hiltでviewModelを注入して、flowをcollect
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(onSearch: () -> Unit, modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier,
        topBar = { SearchTextFieldAppBar(onSearch = onSearch) },
    ) { innerPadding ->
        SearchedResultListField(modifier = Modifier.padding(innerPadding))
    }
}

//TODO searchWordをviewModelで保持するかどうか
@Composable
private fun SearchTextFieldAppBar(
    onSearch: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.surfaceVariant
) {
    val searchWord = rememberSaveable { mutableStateOf("") }

    SearchTextField(
        onSearch = onSearch,
        word = searchWord.value,
        onWordChange = { searchWord.value = it })

}

//TODO keyboardが自動で開かない点を修正
@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
private fun SearchTextField(
    onSearch: () -> Unit,
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
            onSearch()
            keyboardController?.hide()
        }),
        singleLine = true,
    )

}


@Composable
private fun SearchedResultListField(modifier: Modifier = Modifier) {
    //TODO viewModelのListを表示するように変更
    LazyColumn(modifier = modifier) {
        items(20) {
            GithubRepositoryRow(
                fullName = "JetBrains/kotlin",
                stargazersCount = 43500,
                language = "Kotlin",
                modifier = Modifier.fillMaxWidth(),
                onClick = {}
            )
            Divider()
        }
    }
}

@Composable
private fun GithubRepositoryRow(
    fullName: String,
    stargazersCount: Int,
    language: String,
    modifier: Modifier = Modifier,
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
            Text(text = language, fontSize = 12.sp)
        }
    }
}


@Preview
@Composable
private fun SearchScreenPreview() {
    SearchScreen(onSearch = {})
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
    SearchedResultListField()
}

@Preview
@Composable
private fun SearchTextFieldAppBarPreview() {
    SearchTextFieldAppBar(onSearch = {})
}

@Preview
@Composable
private fun SearchTextFieldPreview() {
    SearchTextField(onSearch = {}, word = "", onWordChange = {}, modifier = Modifier)
}