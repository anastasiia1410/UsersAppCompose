package com.example.usersappcompose.ui.screens.list

import com.example.usersappcompose.core.BaseViewModel
import com.example.usersappcompose.data.db.DatabaseRepository
import com.example.usersappcompose.ui.screens.list.use_case.GetContactUseCase
import com.example.usersappcompose.ui.screens.list.use_case.SearchUsersUseCase
import com.example.usersappcompose.ui.screens.list.use_case.SortByOptionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ContactUsersViewModel @Inject constructor(databaseRepository: DatabaseRepository) :
    BaseViewModel<ContactsEvent, ContactsState>(
        useCases = listOf(
            GetContactUseCase(databaseRepository),
            SortByOptionUseCase(),
            SearchUsersUseCase()
        ),
        reducer = ContactsReducer(),
        initialState = ContactsState(
            contacts = emptyList(),
            sortingOption = "",
            searchQuery = "",
            isSearching = false,
            isSorting = false
        )
    ) {

    init {
        getContacts()
    }

    private fun getContacts() {
        handleEvent(ContactsEvent.GetContacts)
    }

    fun sortedList(sortingOption: String) {
        handleEvent(ContactsEvent.SortContacts(sortingOption))
    }

    fun onSearchTextChange(text: String) {
        handleEvent(ContactsEvent.SearchByValue(text))
    }
}