package com.example.usersappcompose.ui.screens.list.ui_state

import com.example.domain.entity.Category
import com.example.domain.entity.Contact
import com.example.domain.use_cases.contact_list.ContactsState

data class ContactsUiState(
    val uuid: String,
    val contacts: List<Contact>,
    val isSorting: Boolean,
    val sortingOption: Category,
    val isSearching: Boolean,
    val searchQuery: String,
)

fun ContactsState.toUiState() : ContactsUiState{
    return ContactsUiState(
        uuid = uuid,
        contacts = contacts,
        isSorting = isSorting,
        sortingOption = sortingOption,
        isSearching = isSearching,
        searchQuery = searchQuery
    )
}
