package com.example.usersappcompose.ui.screens.list

import com.example.usersappcompose.ui.entity.Category
import com.example.usersappcompose.ui.entity.Contact

data class ContactsState(
    val uuid : String,
    val contacts: List<Contact>,
    val isSorting: Boolean,
    val sortingOption: Category,
    val isSearching: Boolean,
    val searchQuery: String,
)