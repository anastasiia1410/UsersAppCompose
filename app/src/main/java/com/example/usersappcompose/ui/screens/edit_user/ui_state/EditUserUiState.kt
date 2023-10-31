package com.example.usersappcompose.ui.screens.edit_user.ui_state

import com.example.domain.use_cases.edit_user.EditState

data class EditUserUiState(
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val email: String,
    val picture: String,
)

fun EditState.toUiState() : EditUserUiState{
    return EditUserUiState(
        firstName =  firstName,
        lastName = lastName,
        phoneNumber = phoneNumber,
        email = email,
        picture = picture
    )
}
