package com.example.usersappcompose.ui.screens.list.use_case

import com.example.usersappcompose.core.UseCase
import com.example.usersappcompose.ui.entity.Category
import com.example.usersappcompose.ui.screens.list.ContactsEvent
import com.example.usersappcompose.ui.screens.list.ContactsState

class SortByOptionUseCase : UseCase<ContactsEvent, ContactsState> {
    override fun canHandle(event: ContactsEvent): Boolean {
        return event is ContactsEvent.SortContacts
    }

    override suspend fun invoke(event: ContactsEvent, state: ContactsState): ContactsEvent {
        return ((event as? ContactsEvent.SortContacts))?.let {
            if (state.sortingOption == Category.ALL.name) {
                return ContactsEvent.GetContacts
            } else {
                val sortingList = state.contacts.filter { it.category == event.sortingOption }
                return ContactsEvent.ReceiveSortedList(sortingList)
            }
        } ?: ContactsEvent.Error("wrong event type: $event")
    }
}