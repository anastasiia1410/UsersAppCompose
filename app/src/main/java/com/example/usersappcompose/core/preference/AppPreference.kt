package com.example.usersappcompose.core.preference

interface AppPreference {

    val currentUser: MutableSet<String>?

    fun saveCurrentUser(
        firstName: String,
        lastName: String,
        phoneNumber: String,
        email: String,
        picture: String,
    )

}