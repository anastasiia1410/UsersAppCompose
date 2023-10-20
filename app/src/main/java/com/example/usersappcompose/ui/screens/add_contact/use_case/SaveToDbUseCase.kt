package com.example.usersappcompose.ui.screens.add_contact.use_case

import com.example.usersappcompose.core.UseCase
import com.example.usersappcompose.data.db.DatabaseRepository
import com.example.usersappcompose.ui.screens.add_contact.AddContactEvent
import com.example.usersappcompose.ui.screens.add_contact.AddContactState

class SaveToDbUseCase(val databaseRepository: DatabaseRepository) :
    UseCase<AddContactEvent, AddContactState> {
    override fun canHandle(event: AddContactEvent): Boolean {
        return event is AddContactEvent.PrepareUserToSave
    }

    override suspend fun invoke(event: AddContactEvent, state: AddContactState): AddContactEvent {
        return ((event) as? AddContactEvent.PrepareUserToSave)?.let {
            databaseRepository.insertUser(event.user)
            return AddContactEvent.SaveUserToContact(event.user)

        } ?: AddContactEvent.Error("wrong event type: $event")
    }
}