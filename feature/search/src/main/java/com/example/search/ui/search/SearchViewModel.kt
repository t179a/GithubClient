package com.example.search.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.search.GithubUserItem
import com.example.data.search.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository
) : ViewModel() {
    private val _searchUiState: MutableStateFlow<SearchUiState> =
        MutableStateFlow(SearchUiState())

    val searchUiState: StateFlow<SearchUiState> = _searchUiState

    fun onSearchUsers(word: String) {
        viewModelScope.launch {
           _searchUiState.update {
               it.copy(isLoading = true)
           }
            searchRepository.searchUsers(word).catch {
                _searchUiState.update {
                    it.copy(isLoading = false, isError = true)
                }
            }.collect {repositoryList ->
                _searchUiState.update {searchUiState ->
                    searchUiState.copy(isLoading = false, userList = repositoryList)
                }
            }
        }
    }

    fun onUpdateSearchWord(searchWord: String) {
        _searchUiState.update {
            it.copy(searchWord = searchWord)
        }
    }
}


data class SearchUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val userList: List<GithubUserItem> = emptyList(),
    val searchWord: String = ""
)