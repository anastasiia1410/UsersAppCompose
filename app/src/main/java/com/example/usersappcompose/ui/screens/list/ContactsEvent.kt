package com.example.usersappcompose.ui.screens.list

import com.example.usersappcompose.ui.entity.User

sealed class ContactsEvent {

    object GetContacts : ContactsEvent()
    data class ShowContacts(val contacts: List<User>) : ContactsEvent()
    data class SortContacts(val sortingOption: String) : ContactsEvent()
    data class ReceiveSortedList(val contacts: List<User>) : ContactsEvent()
    data class SearchByValue( val searchQuery: String) : ContactsEvent()
     data class ShowFoundUsers(val contacts: List<User>) : ContactsEvent()
    data class Error(val message: String) : ContactsEvent()
}