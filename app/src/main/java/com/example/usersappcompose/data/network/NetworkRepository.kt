package com.example.usersappcompose.data.network

import com.example.usersappcompose.ui.entity.User

interface NetworkRepository {

    suspend fun getUsers(): List<User>
}