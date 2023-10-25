package com.example.usersappcompose.ui.screens.add_contact

import com.example.usersappcompose.ui.entity.Contact

sealed class AddContactEvent {

    data class SaveUserToContact(val contact : Contact) : AddContactEvent()
    object MoveToContactsScreen : AddContactEvent()
    data class Error(val message : String) : AddContactEvent()
}