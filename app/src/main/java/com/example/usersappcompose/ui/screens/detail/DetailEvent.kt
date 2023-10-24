package com.example.usersappcompose.ui.screens.detail

import com.example.usersappcompose.ui.entity.User


sealed class DetailEvent {
    data class GetUser(val uuid: String) : DetailEvent()
    data class ShowUser(val user: User) : DetailEvent()
    data class DeleteUser(val user: User) : DetailEvent()
    object None : DetailEvent()
    data class Error(val error: String) : DetailEvent()
}
