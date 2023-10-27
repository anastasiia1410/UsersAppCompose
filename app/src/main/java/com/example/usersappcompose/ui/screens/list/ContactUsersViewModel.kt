package com.example.usersappcompose.ui.screens.list

import com.example.usersappcompose.core.BaseViewModel
import com.example.usersappcompose.core.Router
import com.example.usersappcompose.ui.entity.Category
import com.example.usersappcompose.ui.screens.list.use_case.FilterAndSortContactsUseCase
import com.example.usersappcompose.ui.screens.list.use_case.GetContactUseCase
import com.example.usersappcompose.ui.screens.main.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
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

    val uuid = MutableStateFlow("")

    init {
        getContacts()
    }

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