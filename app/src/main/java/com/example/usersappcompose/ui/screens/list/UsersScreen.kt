package com.example.usersappcompose.ui.screens.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.example.usersappcompose.ui.screens.main.Screen
import com.example.usersappcompose.ui.entity.User
import com.example.usersappcompose.ui.theme.UsersAppComposeTheme

@Composable
fun UsersListScreen(
    viewModel: UserViewModel = hiltViewModel(),
    navController: NavController,
) {
    UsersAppComposeTheme {
        val pager = viewModel.pager.collectAsLazyPagingItems()
        LazyColumn(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            items(pager.itemCount, pager.itemKey { it.uuid }) { index ->
                val user = pager[index]
                user?.let {
                    ListItem(user = it, onUserClick = {
                        navController.navigate(Screen.AddUserToContactScreen.route + "/${user.uuid}")
                    })
                }
            }
        }
    }
}

@Composable
fun ListItem(user: User, onUserClick: ((uuid: String) -> Unit)) {
    UsersAppComposeTheme {
        Row(modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onUserClick.invoke(user.uuid)
            }) {
            Text(
                text = user.firstName,
                style = MaterialTheme.typography.headlineMedium
                    .copy(fontWeight = FontWeight.ExtraBold)
            )
        }
    }
}
