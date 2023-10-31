package com.example.domain.use_cases.edit_user

data class EditState(
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val email: String,
    val picture: String,
)