package com.example.usersappcompose.ui.screens.add_contact

import com.example.usersappcompose.core.Reducer

class AddContactReducer : Reducer<AddContactEvent, AddContactState> {
    override fun reduce(event: AddContactEvent, state: AddContactState): AddContactState {
        return when(event){
            is AddContactEvent.Error -> state
            is AddContactEvent.PrepareUserToSave -> state
            is AddContactEvent.SaveUserToContact -> state.copy(user = event.user)
        }
    }
}