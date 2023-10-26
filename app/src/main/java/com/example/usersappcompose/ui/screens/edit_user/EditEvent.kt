package com.example.usersappcompose.ui.screens.edit_user

sealed class EditEvent {
    object GetSavedUser : EditEvent()
    data class ShowSavedUser(
        val firstName: String,
        val lastName: String,
        val phoneNumber: String,
        val email: String,
        val picture: String,
    ) : EditEvent()

    data class ChangeFirstName(val firstName: String) : EditEvent()
    data class ChangeLastName(val lastName: String) : EditEvent()
    data class ChangePhoneNumber(val phoneNumber: String) : EditEvent()
    data class ChangeEmail(val email: String) : EditEvent()
    data class ChangePicture(val picture: String) : EditEvent()
    object SaveUpdateUser : EditEvent()

    object UpdateUserSaved : EditEvent()
    data class Error(val message: String) : EditEvent()
    object None : EditEvent()
}