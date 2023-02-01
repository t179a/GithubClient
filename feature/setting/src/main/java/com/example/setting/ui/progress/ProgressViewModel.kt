package com.example.setting.ui.progress

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.auth.AuthRepository
import com.example.setting.BuildConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class ProgressViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val encryptedPref: SharedPreferences
) :
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
                _uiState.update { it.copy(loginDate = LocalDateTime.now().toString()) }
                encryptedPref.edit().apply {
                    putString("access_token", authResponse.accessToken)
                }.apply()
            }
        }
    }
}

data class AccountUiState(
    val loginDate: String = ""
)
