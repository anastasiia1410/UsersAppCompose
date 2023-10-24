package com.example.usersappcompose.ui.screens.list

import com.example.usersappcompose.ui.entity.Category
import com.example.usersappcompose.ui.entity.User

data class ContactsState(
    val contacts: List<User>,
    val isSorting : Boolean,
    val sortingOption: String,
    val isSearching: Boolean,
    val searchQuery: String = Category.ALL.name
)