package com.example.usersappcompose.data.db

import com.example.usersappcompose.ui.entity.Category
import com.example.usersappcompose.ui.entity.Contact
import com.example.usersappcompose.ui.entity.User

interface DatabaseRepository {

    suspend fun insertUser(user: User)
    suspend fun updateUserInfo(user: User)
    suspend fun getUser(): User?
    suspend fun insertContact(contact: Contact)
    suspend fun getContacts(): List<Contact>
    suspend fun getContactById(uuid: String): Contact?
    suspend fun getContactsByData(category: Category): List<Contact>

    suspend fun deleteContact(contact: Contact)

}