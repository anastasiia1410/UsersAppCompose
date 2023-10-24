package com.example.usersappcompose.ui.screens.add_contact

import com.example.usersappcompose.ui.entity.User

sealed class AddContactEvent {

    data class SaveUserToContact(val user : User) : AddContactEvent()
    object MoveToContactsScreen : AddContactEvent()
    data class Error(val message : String) : AddContactEvent()
}