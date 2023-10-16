package com.example.usersappcompose.data.db

import com.example.usersappcompose.data.db.entity.toUser
import com.example.usersappcompose.data.db.entity.toUserDatabase
import com.example.usersappcompose.ui.entity.User
import javax.inject.Inject

class DatabaseRepositoryImpl @Inject constructor(private val userDao: UserDao) :
    DatabaseRepository {
    override suspend fun insert(users: List<User>) {
        userDao.insert(users.map { it.toUserDatabase() })
    }

    override suspend fun getUsers(): List<User> {
        return userDao.getUsers().map { it.toUser() }
    }

    override suspend fun getUserById(uuid: String): User {
        return userDao.getUserById(uuid).toUser()
    }

    override suspend fun clearTable() {
        userDao.clearTable()
    }
}