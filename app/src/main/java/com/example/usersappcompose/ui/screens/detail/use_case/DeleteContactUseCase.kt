package com.example.usersappcompose.ui.screens.detail.use_case

import com.example.usersappcompose.core.UseCase
import com.example.usersappcompose.data.db.DatabaseRepository
import com.example.usersappcompose.ui.screens.detail.DetailEvent
import com.example.usersappcompose.ui.screens.detail.DetailState
import java.sql.SQLException
import javax.inject.Inject

class DeleteContactUseCase @Inject constructor(private val databaseRepository: DatabaseRepository) :
    UseCase<DetailEvent, DetailState> {
    override fun canHandle(event: DetailEvent): Boolean {
        return event is DetailEvent.DeleteUser
    }

    override suspend fun invoke(event: DetailEvent, state: DetailState): DetailEvent {
        return ((event as? DetailEvent.DeleteUser))?.let {
            try {
                if(state.contact != null) {
                    databaseRepository.deleteContact(state.contact)
                }
                return DetailEvent.UserDeleted
            } catch (e: SQLException) {
                return DetailEvent.Error("error $e")
            }
        } ?: DetailEvent.Error("wrong event type: $event")
    }
}