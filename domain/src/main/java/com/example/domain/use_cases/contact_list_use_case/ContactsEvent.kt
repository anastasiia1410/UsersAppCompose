package com.example.domain.use_cases.contact_list_use_case

import com.example.domain.entity.Category
import com.example.domain.entity.Contact

sealed class ContactsEvent {

    object GetContacts : ContactsEvent()
    data class ShowContacts(val contacts: List<Contact>) : ContactsEvent()
    data class FilterAndSearch(val searchQuery: String, val sortingOption: Category) :
        ContactsEvent()

    data class ShowFoundUsers(val contacts: List<Contact>) : ContactsEvent()
    data class Error(val message: String) : ContactsEvent()
}