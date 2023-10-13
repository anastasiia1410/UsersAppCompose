package com.example.usersappcompose.data.network

import com.example.usersappcompose.screens.entity.User

interface NetworkRepository {

    suspend fun getUsers(): List<User>
}