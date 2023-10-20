package com.example.usersappcompose.data.db

import com.example.usersappcompose.ui.entity.User

interface DatabaseRepository {

    suspend fun insertUser(user: User)
    suspend fun insert(users: List<User>)

    suspend fun getCurrentUser() : User?

    suspend fun getUsers(): List<User>

    suspend fun getUserById(uuid: String) : User

    suspend fun updateUserInfo(user: User)

}