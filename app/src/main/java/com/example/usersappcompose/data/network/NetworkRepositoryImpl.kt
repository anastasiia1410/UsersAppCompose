package com.example.usersappcompose.data.network

import com.example.usersappcompose.data.network.entity.toUser
import com.example.usersappcompose.ui.entity.User
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(private val api: Api) : NetworkRepository {
    override suspend fun getUsers(): List<User> {
        return api.getUsers().userList.map {
            it.toUser()
        }
    }
}