package com.example.usersappcompose.ui.screens.list

import com.example.usersappcompose.ui.entity.Category
import com.example.usersappcompose.ui.entity.Contact

sealed class ContactsEvent {

    object GetContacts : ContactsEvent()
    data class ShowContacts(val contacts: List<Contact>) : ContactsEvent()
    data class FilterAndSearch(val searchQuery: String, val sortingOption: Category) :
        ContactsEvent()

    data class ShowFoundUsers(val contacts: List<Contact>) : ContactsEvent()
    data class Error(val message: String) : ContactsEvent()
}