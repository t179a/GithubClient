package com.example.search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.cash.molecule.AndroidUiDispatcher
import app.cash.molecule.RecompositionClock
import app.cash.molecule.launchMolecule
import com.example.data.search.UserDetailRepository
import com.example.data.search.UserItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.PersistentList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    userDetailRepository: UserDetailRepository
) : ViewModel() {
    private val userName: String = requireNotNull(savedStateHandle.get<String>("userName"))
    private val followers = userDetailRepository.getFollowers(userName)
    private val following = userDetailRepository.getFollowing(userName)
    private val moleculeScope =
        CoroutineScope(viewModelScope.coroutineContext + AndroidUiDispatcher.Main)
    val userDetailUiState: StateFlow<UserDetailUiState> =
        moleculeScope.launchMolecule(clock = RecompositionClock.ContextClock) {
            userDetailPresenter(followingFlow = following, followersFlow = followers)
        }

    @Composable
    fun userDetailPresenter(
        followingFlow: Flow<PersistentList<UserItem>>,
        followersFlow: Flow<PersistentList<UserItem>>
    ): UserDetailUiState {
        val followingList by followingFlow.collectAsState(initial = null)
        val followersList by followersFlow.collectAsState(initial = null)
        return if (followersList == null || followingList == null) {
            UserDetailUiState(isLoading = true)
        } else {
            UserDetailUiState(
                isLoading = false,
                followingList = followingList!!,
                followersList = followersList!!
            )
        }
    }
}

data class UserDetailUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val followingList: List<UserItem> = emptyList(),
    val followersList: List<UserItem> = emptyList()
)