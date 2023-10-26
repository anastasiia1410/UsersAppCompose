package com.example.usersappcompose.ui.screens.add_contact

import com.example.usersappcompose.core.Reducer

class AddContactReducer : Reducer<AddContactEvent, AddContactState> {
    override fun reduce(event: AddContactEvent, state: AddContactState): AddContactState {
        return when(event){
            is AddContactEvent.Error -> state
            is AddContactEvent.UserSaved -> state
            is AddContactEvent.SaveUserToContact -> state.copy(category = event.contact.category)
        }
    }
}