package com.example.usersappcompose.ui.screens.list.use_case

import com.example.usersappcompose.core.UseCase
import com.example.usersappcompose.ui.screens.list.ContactsEvent
import com.example.usersappcompose.ui.screens.list.ContactsState
import kotlinx.coroutines.delay

class SearchUsersUseCase : UseCase<ContactsEvent, ContactsState> {
    override fun canHandle(event: ContactsEvent): Boolean {
        return event is ContactsEvent.SearchByValue
    }

    override suspend fun invoke(event: ContactsEvent, state: ContactsState): ContactsEvent {
        return ((event as? ContactsEvent.SearchByValue))?.let {
            if (event.searchQuery.isNotBlank()) {
                delay(2000L)
                val updateList = state.contacts.filter {
                    it.doesMatchSearchQuery(event.searchQuery)
                }
                ContactsEvent.ShowFoundUsers(updateList)
            } else {
                ContactsEvent.GetContacts
            }
        } ?: ContactsEvent.Error("wrong event type: $event")
    }
}