package com.example.usersappcompose.data.network

import com.example.usersappcompose.ui.entity.Contact

interface NetworkRepository {

    suspend fun getContacts(): List<Contact>
}