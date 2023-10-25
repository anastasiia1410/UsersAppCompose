package com.example.usersappcompose.data.db

import com.example.usersappcompose.ui.entity.User

interface DatabaseRepository {

    suspend fun insertUser(user: User)

    suspend fun getUsers(uuid: String): List<User>

    suspend fun getUserById(uuid: String): User?

    suspend fun getUsersByCategory(category: String): List<User>

    suspend fun updateUserInfo(user: User)

    suspend fun deleteUser(user: User)

}