package com.example.usersappcompose.ui.screens.list

import androidx.lifecycle.viewModelScope
import com.example.domain.entity.Category
import com.example.domain.use_cases.contact_list.ContactsEvent
import com.example.domain.use_cases.contact_list.ContactsReducer
import com.example.domain.use_cases.contact_list.ContactsState
import com.example.domain.use_cases.contact_list.use_cases.FilterAndSortContactsUseCase
import com.example.domain.use_cases.contact_list.use_cases.GetContactUseCase
import com.example.usersappcompose.core.BaseViewModel
import com.example.usersappcompose.core.Router
import com.example.usersappcompose.ui.screens.list.ui_state.ContactsUiState
import com.example.usersappcompose.ui.screens.list.ui_state.toUiState
import com.example.usersappcompose.ui.screens.main.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
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


    val uiState = _state.map { it.toUiState() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(2000),
            initialValue = ContactsUiState(
                contacts = emptyList(),
                sortingOption = Category.ALL,
                searchQuery = "",
                isSearching = false,
                isSorting = false,
                uuid = ""
            )
        )


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