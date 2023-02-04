package com.example.search

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.example.search.ui.detail.UserDetailScreen
import com.example.search.ui.detail.UserDetailUiState
import com.example.testing.data.githubRepositoryItemTestData
import com.example.testing.data.githubUserItemTestData
import org.junit.Rule
import org.junit.Test

class UserDetailScreenTest {
    @get: Rule
    val composeTestRule = createComposeRule()

    @Test
    fun circularProgressIndicator_whenUserDetailStateIsLoading_displayed() {
        composeTestRule.setContent {
            UserDetailScreen(
                uiState = UserDetailUiState.Loading,
                onClick = {}
            )
        }
        composeTestRule.onNodeWithTag("circular_progress_indicator").assertIsDisplayed()
    }

    @Test
    fun userName_is_displayed() {
        composeTestRule.setContent {
            UserDetailScreen(
                uiState = UserDetailUiState.UiState(
                    userItem = githubUserItemTestData[0],
                    followingList = githubUserItemTestData,
                    followersList = githubUserItemTestData,
                    repositoryList = githubRepositoryItemTestData
                ),
                onClick = {}
            )
        }
        composeTestRule.onNodeWithTag("user_name").assertIsDisplayed()
    }

    @Test
    fun followingListRow_is_displayed() {
        composeTestRule.setContent {
            UserDetailScreen(
                uiState = UserDetailUiState.UiState(
                    userItem = githubUserItemTestData[0],
                    followingList = githubUserItemTestData,
                    followersList = githubUserItemTestData,
                    repositoryList = githubRepositoryItemTestData
                ),
                onClick = {}
            )
        }
        composeTestRule.onNodeWithTag("following_list_row").assertIsDisplayed()
    }

    @Test
    fun repositoryCardRow_is_Exists() {
        composeTestRule.setContent {
            UserDetailScreen(
                uiState = UserDetailUiState.UiState(
                    userItem = githubUserItemTestData[0],
                    followingList = githubUserItemTestData,
                    followersList = githubUserItemTestData,
                    repositoryList = githubRepositoryItemTestData
                ),
                onClick = {}
            )
        }
        composeTestRule.onNodeWithTag("repository_card_row").assertExists()
    }



}