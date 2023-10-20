package com.example.usersappcompose.ui.screens.edit_user

import com.example.usersappcompose.ui.entity.User

sealed class EditEvent {
    object GetSavedUser : EditEvent()
    data class ShowSavedUser(val user: User) : EditEvent()
    data class RefactorSavedUser(val user: User) : EditEvent()
    data class Error(val message: String) : EditEvent()
}