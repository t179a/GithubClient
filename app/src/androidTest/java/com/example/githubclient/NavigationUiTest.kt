package com.example.githubclient

import android.content.SharedPreferences
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.example.githubclient.ui.GithubClientApp
import com.example.testing.HiltComponentActivity
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import javax.inject.Inject

@HiltAndroidTest
class NavigationUiTest {

    @get: Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @BindValue
    @get:Rule(order = 1)
    val tmpFolder: TemporaryFolder = TemporaryFolder.builder().assureDeletion().build()

    @get: Rule(order = 2)
    val composeTestRule = createAndroidComposeRule<HiltComponentActivity>()

    @Inject
    lateinit var encryptedSharedPref: SharedPreferences

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun showBottomBarTest() {
        composeTestRule.setContent {
            GithubClientApp(encryptedPref = encryptedSharedPref)
        }
        composeTestRule.onNodeWithTag("BottomBar").assertIsDisplayed()
    }
}