package com.example.search.ui.search

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.search.GithubUserItem
import com.example.data.search.repository.SearchRepository
import com.example.database.model.GithubUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository,
    private val encryptedSharedPreferences: SharedPreferences
) : ViewModel() {
    private val _searchUiState: MutableStateFlow<SearchUiState> =
        MutableStateFlow(SearchUiState())

    val searchUiState: StateFlow<SearchUiState> = _searchUiState

    fun onSearchUsers(word: String) {
        viewModelScope.launch {
            _searchUiState.update {
                it.copy(isLoading = true)
            }
            val accessToken = encryptedSharedPreferences.getString("access_token", "")
            searchRepository.searchUsers(word = word, accessToken = accessToken!!).catch {
                _searchUiState.update {
                    it.copy(isLoading = false, isError = true)
                }
            }.collect { repositoryList ->
                _searchUiState.update { searchUiState ->
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

    fun onSaveUser(userItem: GithubUserItem) {
        viewModelScope.launch {
            val githubUser = GithubUser(userItem.userId, userItem.userName, userItem.avatarUrl)
            searchRepository.saveUser(githubUser)
        }
    }
}

data class SearchUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val userList: List<GithubUserItem> = emptyList(),
    val searchWord: String = ""
)
