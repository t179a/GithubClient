package com.example.favorite.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.favorite.R


@Composable
fun FavoriteScreen(modifier: Modifier = Modifier, viewModel: FavoriteViewModel = hiltViewModel()){
    val uiState by viewModel.uiState.collectAsState()
    
    if(uiState is FavoriteUiState.Loading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            CircularProgressIndicator()
        }
    } else if(uiState is FavoriteUiState.Success){
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = stringResource(id = R.string.favorite_title), fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.padding(12.dp))
            LazyColumn(modifier = Modifier.fillMaxSize()){
                items((uiState as FavoriteUiState.Success).userList) { user ->
                    GithubUserRow(userName = user.userName, userIconUrl = user.avatarUrl, onClickForDelete = { viewModel.deleteUser(user) })
                    Divider()
                }
            }
        }
    }
}


@Composable
private fun GithubUserRow(
    modifier: Modifier = Modifier,
    userName: String,
    userIconUrl: String,
    onClickForDetail: () -> Unit = {},
    onClickForDelete: () -> Unit = {},

) {
    Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        Row(
            modifier = Modifier
                .clickable(onClick = onClickForDetail)
                .weight(5f)
                .padding(4.dp)
        ) {
            AsyncImage(
                model = userIconUrl,
                contentDescription = "user_icon",
                modifier = Modifier
                    .clip(
                        CircleShape
                    )
                    .size(48.dp),
                placeholder = painterResource(id = R.drawable.ic_android_black_24dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = userName,
                fontSize = 16.sp,
                textAlign = TextAlign.Left,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Image(
            imageVector = Icons.Default.Clear,
            contentDescription = "delete_user",
            modifier = Modifier
                .clickable(onClick = onClickForDelete)
                .weight(1f)
                .padding(horizontal = 24.dp)
                .size(24.dp)
        )
    }
}