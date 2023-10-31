package com.example.domain.use_cases.create_user

data class CreateUserSate(
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val email: String,
    val picture: String,
)
