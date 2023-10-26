package com.example.usersappcompose.ui.screens.create_user

sealed class CreateUserEvent {

    object None : CreateUserEvent()
    data class SetFirstName(val firstName: String) : CreateUserEvent()
    data class SetLastName(val lastName: String) : CreateUserEvent()
    data class SetPhoneNumber(val phoneNumber: String) : CreateUserEvent()
    data class SetEmail(val email: String) : CreateUserEvent()
    data class SetPicture(val picture: String) : CreateUserEvent()
    object ReceiveUser : CreateUserEvent()
    object UserSaved : CreateUserEvent()
    data class Error(val error: String) : CreateUserEvent()
}