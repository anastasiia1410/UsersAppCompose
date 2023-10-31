package com.example.domain.use_cases.contact_list.use_cases

import com.example.core.UseCase
import com.example.domain.repository.db_repository.DatabaseRepository
import com.example.domain.use_cases.contact_list.ContactsEvent
import com.example.domain.use_cases.contact_list.ContactsState

class GetContactUseCase(private val databaseRepository: DatabaseRepository) :
    UseCase<ContactsEvent, ContactsState> {
    override fun canHandle(event: ContactsEvent): Boolean {
        return event is ContactsEvent.GetContacts
    }

    override suspend fun invoke(event: ContactsEvent, state: ContactsState): ContactsEvent {
        return ((event as? ContactsEvent.GetContacts))?.let {
            try {
                val contacts = databaseRepository.getContacts()
                return ContactsEvent.ShowContacts(contacts)
            } catch (e: Exception) {
                return ContactsEvent.Error("something was wrong")
            }
        } ?: ContactsEvent.Error("wrong event type: $event")
    }
}