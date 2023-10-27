package com.example.usersappcompose.ui.screens.list

import com.example.usersappcompose.core.Reducer

class ContactsReducer : Reducer<ContactsEvent, ContactsState> {
    override fun reduce(event: ContactsEvent, state: ContactsState): ContactsState {
        return when (event) {
            is ContactsEvent.Error -> state
            is ContactsEvent.ShowContacts -> state.copy(
                contacts = event.contacts,
                isSorting = false,
                isSearching = false
            )

            is ContactsEvent.FilterAndSearch -> state.copy(
                isSearching = true,
                isSorting = true,
                searchQuery = event.searchQuery,
                sortingOption = event.sortingOption
            )

            is ContactsEvent.ShowFoundUsers -> state.copy(
                isSorting = false,
                isSearching = false,
                contacts = event.contacts
            )

            ContactsEvent.GetContacts -> state
        }
    }
}