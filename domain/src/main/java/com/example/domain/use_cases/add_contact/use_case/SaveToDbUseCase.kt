package com.example.domain.use_cases.add_contact.use_case

import com.example.core.UseCase
import com.example.domain.repository.db_repository.DatabaseRepository
import com.example.domain.use_cases.add_contact.AddContactEvent
import com.example.domain.use_cases.add_contact.AddContactState
import java.sql.SQLException

class SaveToDbUseCase(private val databaseRepository: DatabaseRepository) :
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