package com.example.usersappcompose.ui.screens.create_user.ui_state

import com.example.domain.use_cases.create_user.CreateUserSate

data class CreateUserUiState(
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val email: String,
    val picture: String,
)

fun CreateUserSate.toUiState() : CreateUserUiState{
    return CreateUserUiState(
        firstName =  firstName,
        lastName = lastName,
        phoneNumber = phoneNumber,
        email = email,
        picture = picture
    )
}