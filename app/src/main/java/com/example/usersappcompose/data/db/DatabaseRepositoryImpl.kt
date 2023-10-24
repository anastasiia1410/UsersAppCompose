package com.example.usersappcompose.data.db

import com.example.usersappcompose.data.db.entity.toUser
import com.example.usersappcompose.data.db.entity.toUserDatabase
import com.example.usersappcompose.ui.entity.User
import javax.inject.Inject

class DatabaseRepositoryImpl @Inject constructor(private val userDao: UserDao) :
    DatabaseRepository {
    override suspend fun insertUser(user: User) {
        userDao.insert(user.toUserDatabase())
    }

    override suspend fun getUsers(uuid: String): List<User> {
        return userDao.getUsers(uuid).map { it.toUser() }
    }

    override suspend fun getUserById(uuid: String): User? {
        return userDao.getUserById(uuid)?.toUser()
    }

    override suspend fun updateUserInfo(user: User) {
        userDao.updateUser(user.toUserDatabase())
    }

    override suspend fun deleteUser(user: User) {
        userDao.deleteUser(user.toUserDatabase())
    }
}