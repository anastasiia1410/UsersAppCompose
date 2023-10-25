package com.example.usersappcompose.data.network

import com.example.usersappcompose.data.network.entity.toContact
import com.example.usersappcompose.ui.entity.Contact
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(private val api: Api) : NetworkRepository {
    override suspend fun getContacts(): List<Contact> {
        return api.getContacts().contactList.map {
            it.toContact()
        }
    }
}