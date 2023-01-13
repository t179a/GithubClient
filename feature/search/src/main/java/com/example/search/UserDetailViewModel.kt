package com.example.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.search.UserDetailRepository
import com.example.data.search.UserItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val userDetailRepository: UserDetailRepository
) : ViewModel() {
    private val userName: String = requireNotNull(savedStateHandle.get<String>("userName"))

    //TODO followingのflowとfollowersのflowを一つにまとめてuserDetailStateを更新する

    private val _userDetailState: MutableStateFlow<UserDetailUiState> =
        MutableStateFlow(UserDetailUiState(isLoading = true, isError = false))
    val userDetailState = _userDetailState

    init {
        updateUserDetailState()
    }

    private fun updateUserDetailState() {
        viewModelScope.launch {
            _userDetailState.update {
                it.copy(isLoading = true)
            }
            userDetailRepository.getFollowers(userName).catch {
                _userDetailState.update {
                    it.copy(isLoading = false, isError = true)
                }
            }.collect { userList ->
                _userDetailState.update { userDatailUiState ->
                    userDatailUiState.copy(isLoading = false, followingList = userList)
                }
            }
        }
    }
}


data class UserDetailUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val followingList: List<UserItem> = emptyList(),
    val followersList: List<UserItem> = emptyList()
)