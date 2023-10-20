package com.example.usersappcompose.ui.screens.add_contact

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.example.usersappcompose.R
import com.example.usersappcompose.ui.entity.Category
import com.example.usersappcompose.ui.entity.User
import com.example.usersappcompose.ui.screens.main.Screen

@Composable
fun AddContactScreen(
    viewModel: AddContactViewModel = hiltViewModel(),
    navController: NavController,
) {

    val pager = viewModel.pager.collectAsLazyPagingItems()
    var openDialog by rememberSaveable { mutableStateOf<User?>(null) }
    LazyColumn(
        modifier = Modifier
            .padding(16.dp)
    ) {
        items(pager.itemCount, pager.itemKey { it.uuid }) { index ->
            val user = pager[index]
            user?.let { us ->
                ListItem(user = us, onUserClick = {
                    openDialog = us
                })
                if (openDialog != null && openDialog == us) {
                    CategoryDialog { category ->
                        if (category != null) {
                            openDialog = null
                            viewModel.saveContact(user.copy(category = category.name))
                            navController.navigate(Screen.UsersContactScreen.route)
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun ListItem(user: User, onUserClick: ((uuid: String) -> Unit)) {
    Row(modifier = Modifier
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

@Composable
fun CategoryDialog(onItemClicked: (Category?) -> Unit) {
    val selectedCategory = remember { mutableStateOf<Category?>(null) }
    val familyChecked = remember { mutableStateOf(selectedCategory.value == Category.FAMILY) }
    val friendsChecked = remember { mutableStateOf(selectedCategory.value == Category.FRIENDS) }
    val workChecked = remember { mutableStateOf(selectedCategory.value == Category.WORK) }

    AlertDialog(
        onDismissRequest = {},
        confirmButton = {

            Text(text = stringResource(id = R.string.save), Modifier.clickable {
                onItemClicked(selectedCategory.value)
            })
        },
        title = {
            Text(text = stringResource(id = R.string.choose_user_category))
        },
        text = {
            Column {
                CategoryRow(stringResource(id = R.string.family), familyChecked) {
                    selectedCategory.value = if (it) Category.FAMILY else null
                }
                CategoryRow(stringResource(id = R.string.friends), friendsChecked) {
                    selectedCategory.value = if (it) Category.FRIENDS else null
                }
                CategoryRow(stringResource(id = R.string.work), workChecked) {
                    selectedCategory.value = if (it) Category.WORK else null
                }
            }
        }
    )
}

@Composable
fun CategoryRow(label: String, checked: MutableState<Boolean>, onCheckedChange: (Boolean) -> Unit) {
    Row {
        Checkbox(
            checked = checked.value,
            onCheckedChange = {
                checked.value = it
                onCheckedChange(it)
            }
        )
        Text(text = label, fontSize = 20.sp, modifier = Modifier.padding(8.dp))
    }
}



