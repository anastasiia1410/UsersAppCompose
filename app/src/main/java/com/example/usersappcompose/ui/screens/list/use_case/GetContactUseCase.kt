package com.example.usersappcompose.ui.screens.list.use_case

import com.example.usersappcompose.core.UseCase
import com.example.usersappcompose.data.db.DatabaseRepository
import com.example.usersappcompose.ui.screens.list.ContactsEvent
import com.example.usersappcompose.ui.screens.list.ContactsState
import javax.inject.Inject

class GetContactUseCase @Inject constructor(private val databaseRepository: DatabaseRepository) :
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