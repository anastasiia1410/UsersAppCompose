package com.example.usersappcompose.ui.screens.list.ui_state

import com.example.domain.entity.Category
import com.example.domain.use_cases.contact_list.ContactsState
import com.example.usersappcompose.ui.ui_models.ContactUiModel
import com.example.usersappcompose.ui.ui_models.toContactUiModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

data class ContactsUiState(
    val uuid: String,
    val contacts: ImmutableList<ContactUiModel>,
    val isSorting: Boolean,
    val sortingOption: Category,
    val isSearching: Boolean,
    val searchQuery: String,
)

fun ContactsState.toUiState(): ContactsUiState {
    return ContactsUiState(
        uuid = uuid,
        contacts = contacts.map { it.toContactUiModel() }.toImmutableList(),
        isSorting = isSorting,
        sortingOption = sortingOption,
        isSearching = isSearching,
        searchQuery = searchQuery
    )
}
