package com.example.search.ui

import android.content.res.Configuration.UI_MODE_TYPE_NORMAL
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.data.search.GithubRepositoryItem
import com.example.data.search.GithubUserItem
import com.example.search.R

@Composable
fun EmptyCardRow(
    text: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text)
    }
}

@Composable
fun UserCardRow(
    users: List<GithubUserItem>,
    onUserCardClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(modifier = modifier, contentPadding = PaddingValues(start = 12.dp, end = 12.dp)) {
        items(users) { userItem ->
            UserCardItem(userItem = userItem, onUserCardClick = onUserCardClick)
        }
    }
}

@Composable
fun RepositoryCardRow(
    users: List<GithubRepositoryItem>,
    modifier: Modifier = Modifier
) {
    LazyRow(modifier = modifier, contentPadding = PaddingValues(start = 12.dp, end = 12.dp)) {
        items(users) { repositoryItem ->
            RepositoryCard(repositoryItem = repositoryItem)
        }
    }
}

@Composable
fun UserCardItem(
    userItem: GithubUserItem,
    onUserCardClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .size(width = 170.dp, height = 230.dp)
            .clickable(onClick = { onUserCardClick(userItem.userName) })
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(color = MaterialTheme.colorScheme.secondaryContainer)
    ) {
        Box(
            modifier = Modifier
                .height(170.dp)
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .height(80.dp)
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colorScheme.tertiaryContainer)
            )
            UserImage(
                imageUrl = userItem.avatarUrl,
                modifier = Modifier
                    .size(160.dp)
                    .align(Alignment.BottomCenter)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = userItem.userName,
            modifier = Modifier.padding(top = 8.dp),
            fontSize = 16.sp
        )
    }
}

@Composable
fun UserImage(
    imageUrl: String,
    modifier: Modifier = Modifier,
    elevation: Dp = 5.dp,
    shape: Shape = CircleShape
) {
    Surface(shadowElevation = elevation, shape = shape, modifier = modifier) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current).data(imageUrl).build(),
            contentDescription = "icon",
            placeholder = painterResource(id = R.drawable.ic_android_black_24dp),
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
        )
    }
}

@Composable
fun RepositoryCard(
    repositoryItem: GithubRepositoryItem,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .size(width = 320.dp, height = 160.dp)
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(color = MaterialTheme.colorScheme.secondaryContainer)
    ) {
        Text(
            text = repositoryItem.name,
            modifier = Modifier.padding(8.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Box(
            modifier = Modifier
                .height(60.dp)
                .fillMaxWidth()
        ) {
            repositoryItem.description?.let {
                Text(
                    text = it,
                    modifier = Modifier.padding(8.dp),
                    maxLines = 2,
                    fontSize = 12.sp,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "star_icon",
                tint = Color.Yellow
            )
            Spacer(modifier = Modifier.padding(horizontal = 4.dp))
            Text(
                text = repositoryItem.stargazersCount.toString(),
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.padding(12.dp))
        }
    }
}

@Preview(widthDp = 300, heightDp = 300, uiMode = UI_MODE_TYPE_NORMAL)
@Composable
private fun SnackImagePreview() {
    val sampleUserItem = GithubUserItem(
        userName = "android",
        userId = 123,
        userUrl = "",
        avatarUrl = "",
        followersUrl = "",
        followingUrl = "",
        repositoryUrl = ""
    )
    UserCardItem(userItem = sampleUserItem, onUserCardClick = {})
}

@Preview
@Composable
private fun EmptyRowPreview() {
    EmptyCardRow(text = "No Repository")
}
