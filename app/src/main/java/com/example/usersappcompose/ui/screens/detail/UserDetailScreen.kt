package com.example.usersappcompose.ui.screens.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.usersappcompose.R


@Composable
fun UserDetailScreen(viewModel: UserDetailViewModel) {
    val state by viewModel.uiState.collectAsState()
    val user = state.uiContact
    var showDialog by remember { mutableStateOf(false) }
    if (user != null) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            IconButton(onClick = { showDialog = true }, modifier = Modifier.align(Alignment.End)) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = null,
                    modifier = Modifier
                        .size(48.dp)
                        .padding(8.dp),
                    tint = Color.Red
                )
            }

            if (showDialog) {
                AlertDialog(
                    onDismissRequest = {},
                    confirmButton = {
                        Text(text = stringResource(id = R.string.delete), Modifier.clickable {
                            viewModel.deleteUser()
                            showDialog = false
                        })

                    },
                    title = {
                        Text(text = stringResource(id = R.string.want_delete))
                    },
                    dismissButton = {
                        Text(text = stringResource(id = R.string.cancel), Modifier.clickable {
                            showDialog = false
                        })
                    }
                )
            }

            with(user) {
                AsyncImage(
                    model = picture,
                    contentDescription = null,
                    modifier = Modifier
                        .size(120.dp, 120.dp)
                        .clip(CircleShape)
                )
                Text(
                    text = stringResource(id = R.string.category, category),
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


