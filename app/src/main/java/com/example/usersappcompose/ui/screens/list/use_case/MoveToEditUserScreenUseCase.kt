package com.example.usersappcompose.ui.screens.list.use_case

import com.example.usersappcompose.core.UseCase
import com.example.usersappcompose.ui.screens.list.ContactsEvent
import com.example.usersappcompose.ui.screens.list.ContactsState

class MoveToEditUserScreenUseCase : UseCase<ContactsEvent, ContactsState> {
    override fun canHandle(event: ContactsEvent): Boolean {
        return event is ContactsEvent.ClickToEditUserFAB
    }

    override suspend fun invoke(event: ContactsEvent, state: ContactsState): ContactsEvent {
        return ((event as? ContactsEvent.ClickToEditUserFAB))?.let {
            return ContactsEvent.MoveToEditUserScreen
        } ?: ContactsEvent.Error("wrong event type: $event")
    }
}