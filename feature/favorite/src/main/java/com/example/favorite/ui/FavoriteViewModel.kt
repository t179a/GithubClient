package com.example.favorite.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.favorite.FavoriteRepository
import com.example.database.model.GithubUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val favoriteRepository: FavoriteRepository): ViewModel() {
    val uiState: StateFlow<FavoriteUiState> = favoriteRepository.getAllUser().map { FavoriteUiState.Success(it) }.stateIn(scope = viewModelScope,
    started = SharingStarted.Eagerly, initialValue = FavoriteUiState.Loading)

    fun deleteUser(githubUser: GithubUser) {
        viewModelScope.launch {
            favoriteRepository.deleteUser(githubUser)
        }
    }
}


sealed interface FavoriteUiState {
    data class Success(val userList: List<GithubUser>): FavoriteUiState
    object Loading : FavoriteUiState
}