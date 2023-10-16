package com.example.usersappcompose.ui.screens.detail

import com.example.usersappcompose.ui.entity.User


sealed class DetailEvents {
    data class GetUser(val uuid : String) : DetailEvents()
    data class ShowUser(val user: User) : DetailEvents()
    data class Error(val error: String) : DetailEvents()
}
