package com.example.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.database.dao.GithubUserDao
import com.example.database.model.GithubUser
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GithubUserDaoTest {

    private lateinit var githubUserDao: GithubUserDao
    private lateinit var database: GithubDatabase

    @Before
    fun createDatabase() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context,
            GithubDatabase::class.java
        ).build()
        githubUserDao = database.githubUserDao()
    }

    @After
    fun closeDatabase() {
        database.close()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun githubUserDao_Simple_Read_Write_Test() = runTest {
        val githubUserSample = GithubUser(
            id = 12345,
            userName = "t179a",
            avatarUrl = "https://avatars.githubusercontent.com/u/1024025?v=4"
        )
        githubUserDao.insertUser(githubUserSample)

        val savedGithubUser = githubUserDao.getAllUser().first()

        Assert.assertEquals(githubUserSample.id, savedGithubUser.first().id)
    }
}