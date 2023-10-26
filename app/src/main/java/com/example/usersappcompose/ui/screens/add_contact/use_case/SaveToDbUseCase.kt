package com.example.usersappcompose.ui.screens.add_contact.use_case

import com.example.usersappcompose.core.UseCase
import com.example.usersappcompose.data.db.DatabaseRepository
import com.example.usersappcompose.ui.screens.add_contact.AddContactEvent
import com.example.usersappcompose.ui.screens.add_contact.AddContactState
import java.sql.SQLException
import javax.inject.Inject

class SaveToDbUseCase @Inject constructor(val databaseRepository: DatabaseRepository) :
    UseCase<AddContactEvent, AddContactState> {
    override fun canHandle(event: AddContactEvent): Boolean {
        return event is AddContactEvent.SaveUserToContact
    }

    override suspend fun invoke(event: AddContactEvent, state: AddContactState): AddContactEvent {
        return ((event) as? AddContactEvent.SaveUserToContact)?.let {
            try {
                databaseRepository.insertContact(event.contact)
                return AddContactEvent.UserSaved

            } catch (e: SQLException) {
                return AddContactEvent.Error("error $e")
            }
        } ?: AddContactEvent.Error("wrong event type: $event")
    }
}