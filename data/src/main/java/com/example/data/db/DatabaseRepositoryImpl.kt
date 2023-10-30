package com.example.data.db

import com.example.data.db.entity.toContact
import com.example.data.db.entity.toContactDatabase
import com.example.data.db.entity.toUser
import com.example.data.db.entity.toUserDatabase
import com.example.domain.entity.CURRENT_USER_ID
import com.example.domain.entity.Category
import com.example.domain.entity.Contact
import com.example.domain.entity.User
import com.example.domain.repository.db_repository.DatabaseRepository
import javax.inject.Inject

class DatabaseRepositoryImpl @Inject constructor(
    private val contactDao: ContactDao,
    private val userDao: UserDao,
) :
    DatabaseRepository {
    override suspend fun insertUser(user: User) {
        userDao.insert(user.toUserDatabase())
    }

    override suspend fun updateUserInfo(user: User) {
        userDao.updateUser(user.toUserDatabase())
    }

    override suspend fun getUser(): User? {
        return userDao.getUser()?.toUser()
    }

    override suspend fun insertContact(contact: Contact) {
        contactDao.insert(contact.toContactDatabase())
    }

    override suspend fun getContacts(): List<Contact> {
        return contactDao.getUsers().filter { it.uuid != CURRENT_USER_ID }.map { it.toContact() }
    }

    override suspend fun getContactById(uuid: String): Contact? {
        return contactDao.getUserById(uuid)?.toContact()
    }

    override suspend fun getContactsByData(category: Category): List<Contact> {
        return contactDao.getUsersByData(category).map { it.toContact() }
    }

    override suspend fun deleteContact(contact: Contact) {
        contactDao.deleteUser(contact.toContactDatabase())
    }
}