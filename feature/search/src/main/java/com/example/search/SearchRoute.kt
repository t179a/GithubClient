package com.example.search

import androidx.compose.runtime.Composable

@Composable
fun SearchRoute(
    viewModel: SearchViewModel = hiltViewModel()
){
    SearchScreen(onSearch = { /*TODO*/ })
}