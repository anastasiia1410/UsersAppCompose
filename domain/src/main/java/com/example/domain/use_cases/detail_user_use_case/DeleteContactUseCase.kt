package com.example.domain.use_cases.detail_user_use_case

import com.example.core.UseCase
import com.example.domain.repository.db_repository.DatabaseRepository
import java.sql.SQLException

class DeleteContactUseCase(private val databaseRepository: DatabaseRepository) :
    UseCase<DetailEvent, DetailState> {
    override fun canHandle(event: DetailEvent): Boolean {
        return event is DetailEvent.DeleteUser
    }

    override suspend fun invoke(event: DetailEvent, state: DetailState): DetailEvent {
        return ((event as? DetailEvent.DeleteUser))?.let {
            try {
                if (state.contact != null) {
                    databaseRepository.deleteContact(state.contact)
                }
                return DetailEvent.UserDeleted
            } catch (e: SQLException) {
                return DetailEvent.Error("error $e")
            }
        } ?: DetailEvent.Error("wrong event type: $event")
    }
}