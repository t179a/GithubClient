package com.example.search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

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