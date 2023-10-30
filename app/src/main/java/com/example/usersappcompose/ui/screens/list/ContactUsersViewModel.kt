package com.example.usersappcompose.ui.screens.list

import com.example.domain.entity.Category
import com.example.domain.use_cases.contact_list_use_case.ContactsEvent
import com.example.domain.use_cases.contact_list_use_case.ContactsReducer
import com.example.domain.use_cases.contact_list_use_case.ContactsState
import com.example.domain.use_cases.contact_list_use_case.FilterAndSortContactsUseCase
import com.example.domain.use_cases.contact_list_use_case.GetContactUseCase
import com.example.usersappcompose.core.BaseViewModel
import com.example.usersappcompose.core.Router
import com.example.usersappcompose.ui.screens.main.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ContactUsersViewModel @Inject constructor(
    private val router: Router,
    getContactUseCase: GetContactUseCase,
    filterAndSortContactsUseCase: FilterAndSortContactsUseCase,
) :
    BaseViewModel<ContactsEvent, ContactsState>(
        useCases = listOf(
            getContactUseCase,
            filterAndSortContactsUseCase,
        ),
        reducer = ContactsReducer(),
        initialState = ContactsState(
            contacts = emptyList(),
            sortingOption = Category.ALL,
            searchQuery = "",
            isSearching = false,
            isSorting = false,
            uuid = ""
        )
    ) {

    fun getContacts() {
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

    fun moveToAddContactScreen() {
        router.navigate(Screen.AddContactScreen.route)
    }

    fun moveToEditUserScreen() {
        router.navigate(Screen.EditUserScreen.route)
    }

    fun moveToDetailUserScreen(uuid: String) {
        router.navigate(Screen.UserDetailScreen.route + "/${uuid}")
    }
}