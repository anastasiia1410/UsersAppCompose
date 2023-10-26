package com.example.usersappcompose.ui.screens.list.use_case

import com.example.usersappcompose.core.UseCase
import com.example.usersappcompose.ui.screens.list.ContactsEvent
import com.example.usersappcompose.ui.screens.list.ContactsState

class MoveToAddContactScreenUseCase : UseCase<ContactsEvent, ContactsState>{
    override fun canHandle(event: ContactsEvent): Boolean {
        return event is ContactsEvent.ClickToAddFAB
    }

    override suspend fun invoke(event: ContactsEvent, state: ContactsState): ContactsEvent {
       return ((event as? ContactsEvent.ClickToAddFAB))?.let {
           return ContactsEvent.MoveToAddContactScreen
       } ?: ContactsEvent.Error("wrong event type: $event")
    }
}