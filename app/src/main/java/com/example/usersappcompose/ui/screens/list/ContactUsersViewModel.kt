package com.example.usersappcompose.ui.screens.list

import com.example.usersappcompose.core.BaseViewModel
import com.example.usersappcompose.ui.entity.Category
import com.example.usersappcompose.ui.screens.list.use_case.FilterAndSortContactsUseCase
import com.example.usersappcompose.ui.screens.list.use_case.GetContactUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ContactUsersViewModel @Inject constructor(
    getContactUseCase: GetContactUseCase,
    filterAndSortContactsUseCase: FilterAndSortContactsUseCase,
) :
    BaseViewModel<ContactsEvent, ContactsState>(
        useCases = listOf(
            getContactUseCase,
            filterAndSortContactsUseCase
        ),
        reducer = ContactsReducer(),
        initialState = ContactsState(
            contacts = emptyList(),
            sortingOption = Category.ALL,
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

    fun sortedList(sortingOption: Category) {
        handleEvent(
            ContactsEvent.FilterAndSearch(
                state.value.searchQuery,
                sortingOption
            )
        )
    }

    fun onSearchTextChange(text: String) {
        handleEvent(ContactsEvent.FilterAndSearch(text, state.value.sortingOption))
    }
}