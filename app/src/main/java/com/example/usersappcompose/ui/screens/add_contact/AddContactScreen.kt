package com.example.usersappcompose.ui.screens.add_contact

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.example.domain.entity.Category
import com.example.usersappcompose.R
import com.example.usersappcompose.ui.ui_models.ContactUiModel
import com.example.usersappcompose.ui.ui_models.toContactUiModel

@Composable
fun AddContactScreen(
    viewModel: AddContactViewModel = hiltViewModel(),
) {

    val pager = viewModel.pager.collectAsLazyPagingItems()

    LazyColumn(
        contentPadding = PaddingValues(16.dp)
    ) {
        items(pager.itemCount, pager.itemKey { it.uuid }) { index ->
            val contact = pager[index]
            contact?.also { cont ->
                ListItem(cont.toContactUiModel(), onSaveContact = {
                    viewModel.saveContact(cont, it)
                })
            }
        }
    }
}


@Composable
fun ListItem(
    contact: ContactUiModel,
    onSaveContact: (Category) -> Unit,
) {
    var showDialog by remember { mutableStateOf(false) }
    Row(modifier = Modifier
        .clickable {
            showDialog = true
        }) {
        Text(
            text = contact.firstName,
            style = MaterialTheme.typography.headlineMedium
                .copy(fontWeight = FontWeight.ExtraBold)
        )
    }
    if (showDialog) {
        CategoryDialog(onItemClicked = { category ->
            if (category != null) {
                showDialog = false
                onSaveContact.invoke(category)

            }
        }, onCancelClick = {
            showDialog = it
        })
    }
}

@Composable
fun CategoryDialog(onItemClicked: (Category?) -> Unit, onCancelClick: (Boolean) -> Unit) {
    val selectedCategory = remember { mutableStateOf<Category?>(null) }
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
                CategoryRow(stringResource(id = R.string.family), selectedCategory, Category.FAMILY)
                CategoryRow(
                    stringResource(id = R.string.friends),
                    selectedCategory,
                    Category.FRIENDS
                )
                CategoryRow(stringResource(id = R.string.work), selectedCategory, Category.WORK)
            }
        },
        dismissButton = {
            Text(text = stringResource(id = R.string.cancel),
                Modifier
                    .clickable {
                        onCancelClick.invoke(false)
                    }
                    .padding(end = 16.dp))
        }
    )
}

@Composable
fun CategoryRow(label: String, selectedCategory: MutableState<Category?>, category: Category?) {
    val checked = remember { derivedStateOf { selectedCategory.value == category } }
    Row {
        Checkbox(
            checked = checked.value,
            onCheckedChange = { newChecked ->
                selectedCategory.value = if (newChecked) category else null
            }
        )
        Text(text = label, fontSize = 20.sp, modifier = Modifier.padding(8.dp))
    }
}



