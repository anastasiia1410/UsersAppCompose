package com.example.data.network

import com.example.data.network.entity.toContact
import com.example.domain.entity.Contact
import com.example.domain.repository.network_repository.NetworkRepository
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(private val api: Api) : NetworkRepository {
    override suspend fun getContacts(): List<Contact> {
        return api.getContacts().contactList.map { it.toContact() }
    }
}