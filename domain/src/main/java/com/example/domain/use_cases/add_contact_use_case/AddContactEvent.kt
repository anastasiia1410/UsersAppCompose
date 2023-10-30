package com.example.domain.use_cases.add_contact_use_case

import com.example.domain.entity.Contact

sealed class AddContactEvent {

    data class SaveUserToContact(val contact : Contact) : AddContactEvent()
    object UserSaved : AddContactEvent()
    data class Error(val message : String) : AddContactEvent()
}