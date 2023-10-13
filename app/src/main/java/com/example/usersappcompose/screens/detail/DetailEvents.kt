package com.example.usersappcompose.screens.detail

import com.example.usersappcompose.screens.entity.User


sealed class DetailEvents {
    data class GetUser(val uuid : String) : DetailEvents()
    data class ShowUser(val user: User) : DetailEvents()
    data class Error(val error: String) : DetailEvents()
}
