package com.example.usersappcompose.ui.screens.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.usersappcompose.R

@Composable
fun UserDetailScreen(viewModel: UserDetailViewModel) {
    val state by viewModel.state.collectAsState()
    val user = state.user
    if (user != null) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            with(user) {
                AsyncImage(
                    model = picture,
                    contentDescription = null,
                    modifier = Modifier.size(120.dp, 120.dp).clip(CircleShape)
                )
                Text(
                    text = stringResource(id = R.string.category, category!!),
                    Modifier.padding(16.dp)
                )
                Text(
                    text = stringResource(id = R.string.name, firstName, lastName),
                    Modifier.padding(16.dp)
                )
                Text(text = stringResource(id = R.string.mail, email), Modifier.padding(16.dp))
            }
        }
    }
}


