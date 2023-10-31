package com.example.domain.use_cases.contact_list

import com.example.domain.entity.Category
import com.example.domain.entity.Contact

data class ContactsState(
    val uuid: String,
    val contacts: List<Contact>,
    val isSorting: Boolean,
    val sortingOption: Category,
    val isSearching: Boolean,
    val searchQuery: String,
)