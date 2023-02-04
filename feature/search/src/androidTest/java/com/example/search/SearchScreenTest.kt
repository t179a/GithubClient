package com.example.search

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.example.search.ui.search.SearchScreen
import com.example.search.ui.search.SearchUiState
import com.example.testing.data.githubUserItemTestData
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

    @Test
    fun githubUserRow_whenUiStateIsSuccess_displayed() {
        composeTestRule.setContent {
            SearchScreen(
                onSearch = {},
                onWordChange = {},
                onClickForDetail = {},
                onClickForSave = {},
                uiState = SearchUiState(userList = githubUserItemTestData.take(1))
            )
        }
        composeTestRule.onNodeWithTag("github_user_row").assertIsDisplayed()
    }

    @Test
    fun textField_is_displayed(){
        composeTestRule.setContent {
            SearchScreen(
                onSearch = {},
                onWordChange = {},
                onClickForDetail = {},
                onClickForSave = {},
                uiState = SearchUiState()
            )
        }
        composeTestRule.onNodeWithTag("text_field").assertIsDisplayed()
    }
}