package com.example.search

import android.content.SharedPreferences
import com.example.search.ui.search.SearchViewModel
import com.example.testing.MainDispatcherRule
import com.example.testing.repository.TestSearchRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.kotlin.mock

class SearchViewModelTest {
    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: SearchViewModel
    private lateinit var mockSharedPreferences: SharedPreferences
    private val searchRepository = TestSearchRepository()

    @Before
    fun setup() {
        mockSharedPreferences = mock()
        `when`(mockSharedPreferences.getString("access_token", "")).thenReturn("")
        viewModel = SearchViewModel(
            searchRepository = searchRepository,
            encryptedSharedPreferences = mockSharedPreferences
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun uiState_whenInitialized_thenNotShowLoading() = runTest {
        Assert.assertFalse(viewModel.searchUiState.value.isLoading)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun uiState_whenUpdateSearchWord_thenSearchWordIsChanged() = runTest {
        viewModel.onUpdateSearchWord("t179a")
        Assert.assertEquals(viewModel.searchUiState.value.searchWord, "t179a")
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun uiState_whenSearchUsers_thenUserListIsFetched() = runTest {
        viewModel.onSearchUsers("")
        Assert.assertEquals(viewModel.searchUiState.value.userList[0].userName, "android")
    }
}
