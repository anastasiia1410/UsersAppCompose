package com.example.domain.repository.network_repository

import com.example.domain.entity.Contact

interface NetworkRepository {

    suspend fun getContacts(): List<Contact>
}