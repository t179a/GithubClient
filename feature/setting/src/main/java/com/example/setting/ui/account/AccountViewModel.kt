package com.example.setting.ui.account

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(private val encryptedPref: SharedPreferences) : ViewModel() {

    fun deleteAccessToken() {
        viewModelScope.launch {
            encryptedPref.edit().apply {
                remove("access_token")
            }.apply()
        }
    }
}
