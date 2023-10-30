package com.example.domain.use_cases.contact_list_use_case

import com.example.core.UseCase
import com.example.domain.entity.Category
import com.example.domain.repository.db_repository.DatabaseRepository
import java.sql.SQLException

class FilterAndSortContactsUseCase(private val databaseRepository: DatabaseRepository) :
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

