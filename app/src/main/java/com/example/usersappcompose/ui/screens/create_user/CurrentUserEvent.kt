package com.example.usersappcompose.ui.screens.create_user

import com.example.usersappcompose.ui.entity.User

sealed class CurrentUserEvent {

    data class AddUser(
        val uuid : String = "1",
        val firstName: String,
        val lastName: String,
        val phoneNumber: String?,
        val email: String,
        val picture: String,
    ) : CurrentUserEvent()

    data class SaveCurrentUser(val currentUser: User) : CurrentUserEvent()
    data class Error(val error: String) : CurrentUserEvent()
}