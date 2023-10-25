package com.example.usersappcompose.ui.entity

const val CURRENT_USER_ID = "1"

data class User(
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val email: String,
    val uuid: String,
    val picture: String,
)