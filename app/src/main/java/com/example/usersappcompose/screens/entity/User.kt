package com.example.usersappcompose.screens.entity

import com.example.usersappcompose.screens.detail.DetailState


data class User(
    val gender: String,
    val title: String,
    val firstName: String,
    val lastName: String,
    val city: String,
    val state: String,
    val country: String,
    val postCode: String,
    val email: String,
    val uuid: String,
    val picture: String,
) {
    companion object {
        fun initialUser(): DetailState {
            return DetailState(null)
        }
    }
}