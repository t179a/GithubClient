package com.example.setting.ui.progress

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.auth.AuthRepository
import com.example.setting.BuildConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProgressViewModel @Inject constructor(private val authRepository: AuthRepository) :
    ViewModel() {
    private val _uiState = MutableStateFlow(AccountUiState())
    val uiState = _uiState
    fun getAccessToken(code: String) {
        viewModelScope.launch {
            authRepository.postCertificationInfo(
                clientId = BuildConfig.CLIENT_ID,
                clientSecret = BuildConfig.CLIENT_SECRET,
                code = code
            ).collect { authResponse ->
                _uiState.update { it.copy(accessToken = authResponse.accessToken) }
            }
        }
    }
}

data class AccountUiState(
    val accessToken: String = ""
)
