package com.example.domain.repository.db_repository

import com.example.domain.entity.Category
import com.example.domain.entity.Contact
import com.example.domain.entity.User

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