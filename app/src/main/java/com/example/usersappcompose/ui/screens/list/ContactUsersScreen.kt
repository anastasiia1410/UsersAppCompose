@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.usersappcompose.ui.screens.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.House
import androidx.compose.material.icons.filled.SortByAlpha
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.usersappcompose.R
import com.example.usersappcompose.ui.entity.Category
import com.example.usersappcompose.ui.entity.User
import com.example.usersappcompose.ui.screens.main.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsersListScreen(
    viewModel: ContactUsersViewModel = hiltViewModel(),
    navController: NavController,
) {


    val state by viewModel.state.collectAsState()


    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier.fillMaxWidth()) {
            SortingMenu(
                onSortingOptionSelected = viewModel::sortedList,
                dropDownMenuItem = DropDownMenuItem.menuList(),
            )
            TextField(
                value = state.searchQuery,
                onValueChange = viewModel::onSearchTextChange,
                placeholder = { Text(text = stringResource(id = R.string.search)) },
                modifier = Modifier.padding(8.dp)
            )
        }
        if (state.isSearching) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        } else {
            Box(modifier = Modifier.fillMaxSize()) {

                LazyColumn(
                    contentPadding = PaddingValues(
                        horizontal = 16.dp,
                        vertical = 32.dp
                    )
                ) {
                    items(items = state.contacts) { user ->
                        ListItem(user = user, onUserClick = {
                            navController.navigate(Screen.UserDetailScreen.route + "/${user.uuid}")
                        })
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 4.dp),
                    horizontalArrangement = Arrangement.End
                ) {

                    EditUserButton(navController = navController)
                }

                Row(
                    modifier = Modifier
                        .padding(end = 4.dp, bottom = 4.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.Bottom
                ) {
                    AddContactButton(navController = navController)
                }
            }
        }
    }
}

@Composable
fun ListItem(user: User, onUserClick: ((uuid: String) -> Unit)) {

    Row(modifier = Modifier
        .padding(4.dp)
        .clickable {
            onUserClick.invoke(user.uuid)
        }) {
        Text(
            text = user.firstName,
            style = MaterialTheme.typography.headlineMedium
                .copy(fontWeight = FontWeight.Black)
        )
    }
}

@Composable
fun EditUserButton(navController: NavController) {
    Box(
        contentAlignment = Alignment.TopEnd,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        FloatingActionButton(
            onClick = { navController.navigate(Screen.EditUserScreen.route) },
            shape = CircleShape,
        ) {
            Icon(Icons.Filled.House, null)
        }
    }
}

@Composable
fun AddContactButton(navController: NavController, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.BottomEnd,
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        FloatingActionButton(
            onClick = { navController.navigate(Screen.AddContactScreen.route) },
            shape = CircleShape,
        ) {
            Icon(Icons.Filled.Add, null)
        }
    }
}


@Composable
fun SortingMenu(
    dropDownMenuItem: List<DropDownMenuItem>,
    onSortingOptionSelected: (String) -> Unit,
) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    IconButton(
        onClick = {
            expanded = true
        }, modifier = Modifier
            .padding(vertical = 16.dp, horizontal = 32.dp)
            .size(40.dp, 40.dp)

    ) {
        Icon(
            Icons.Default.SortByAlpha, contentDescription = null
        )
    }

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        dropDownMenuItem.forEach { item ->
            DropdownMenuItem(
                text = { Text(text = item.text) },
                onClick = {
                    onSortingOptionSelected.invoke(item.text)
                    expanded = false
                })
        }
    }
}

data class DropDownMenuItem(val text: String) {
    companion object {
        fun menuList(): List<DropDownMenuItem> {
            return listOf(
                DropDownMenuItem(Category.ALL.name),
                DropDownMenuItem(Category.FAMILY.name),
                DropDownMenuItem(Category.FRIENDS.name),
                DropDownMenuItem(Category.WORK.name)
            )
        }
    }
}
