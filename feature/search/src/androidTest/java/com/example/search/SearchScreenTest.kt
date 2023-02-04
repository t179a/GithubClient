package com.example.search

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.example.search.ui.search.SearchScreen
import com.example.search.ui.search.SearchUiState
import org.junit.Rule
import org.junit.Test

class SearchScreenTest {
    @get: Rule
    val composeTestRule = createComposeRule()

    @Test
    fun circularProgressIndicator_whenUiStateIsLoading_displayed() {
        composeTestRule.setContent {
            SearchScreen(
                onSearch = {},
                onWordChange = {},
                onClickForDetail = {},
                onClickForSave = {},
                uiState = SearchUiState(isLoading = true)
            )
        }
        composeTestRule.onNodeWithTag("circular_progress_indicator").assertIsDisplayed()
    }
}