package com.example.usersappcompose.ui.screens.list.use_case

import com.example.usersappcompose.core.UseCase
import com.example.usersappcompose.data.db.DatabaseRepository
import com.example.usersappcompose.ui.entity.Category
import com.example.usersappcompose.ui.screens.list.ContactsEvent
import com.example.usersappcompose.ui.screens.list.ContactsState
import java.sql.SQLException

class FilterAndSortContactsUseCase(val databaseRepository: DatabaseRepository) :
    UseCase<ContactsEvent, ContactsState> {
    override fun canHandle(event: ContactsEvent): Boolean {
        return event is ContactsEvent.FilterAndSearch
    }

    override suspend fun invoke(event: ContactsEvent, state: ContactsState): ContactsEvent {
        return ((event as? ContactsEvent.FilterAndSearch))?.let {
            if (event.sortingOption == Category.ALL && event.searchQuery.isBlank()) {
                ContactsEvent.GetContacts
            } else {
                try {
                    val sortingList = if (event.sortingOption != Category.ALL) {
                        databaseRepository.getContactsByData(event.sortingOption)
                    } else {
                        databaseRepository.getContacts()
                    }

                    val updatedList = sortingList.filter {
                        it.doesMatchSearchQuery(event.searchQuery)
                    }
                    ContactsEvent.ShowFoundUsers(updatedList)
                } catch (e: SQLException) {
                    ContactsEvent.Error("Something went wrong")
                }
            }
        } ?: ContactsEvent.Error("wrong event type: $event")
    }
}

