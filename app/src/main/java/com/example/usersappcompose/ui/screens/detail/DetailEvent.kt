package com.example.usersappcompose.ui.screens.detail

import com.example.usersappcompose.ui.entity.Contact


sealed class DetailEvent {
    data class GetUser(val uuid: String) : DetailEvent()
    data class ShowUser(val contact: Contact) : DetailEvent()
    object DeleteUser : DetailEvent()
    object UserDeleted : DetailEvent()
    object None : DetailEvent()
    data class Error(val error: String) : DetailEvent()
}
