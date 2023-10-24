package com.example.usersappcompose.ui.screens.list

import com.example.usersappcompose.core.Reducer

class ContactsReducer : Reducer<ContactsEvent, ContactsState> {
    override fun reduce(event: ContactsEvent, state: ContactsState): ContactsState {
        return when (event) {
            is ContactsEvent.Error -> state
            ContactsEvent.GetContacts -> state.copy(isSearching = false, isSorting = false)
            is ContactsEvent.ShowContacts -> state.copy(contacts = event.contacts)
            is ContactsEvent.SearchByValue -> state.copy(
                isSearching = true,
                searchQuery = event.searchQuery
            )

            is ContactsEvent.ShowFoundUsers -> state.copy(
                isSearching = false,
                contacts = event.contacts
            )

            is ContactsEvent.SortContacts -> state.copy(
                sortingOption = event.sortingOption,
                isSorting = true
            )

            is ContactsEvent.ReceiveSortedList -> state.copy(
                isSorting = false,
                contacts = event.contacts
            )
        }
    }
}