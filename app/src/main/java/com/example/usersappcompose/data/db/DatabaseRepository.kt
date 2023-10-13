package com.example.usersappcompose.data.db

import com.example.usersappcompose.screens.entity.User

interface DatabaseRepository {
    suspend fun insert(users: List<User>)

    suspend fun getUsers(): List<User>

    suspend fun getUserById(uuid: String) : User

    suspend fun clearTable()

}