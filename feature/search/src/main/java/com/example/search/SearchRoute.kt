package com.example.search

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SearchRoute(
    viewModel: SearchViewModel = hiltViewModel()
){
    SearchScreen(onSearch = { /*TODO*/ })
}