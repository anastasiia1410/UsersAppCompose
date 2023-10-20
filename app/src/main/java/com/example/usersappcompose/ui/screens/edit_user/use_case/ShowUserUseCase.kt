package com.example.usersappcompose.ui.screens.edit_user.use_case

import com.example.usersappcompose.core.UseCase
import com.example.usersappcompose.data.db.DatabaseRepository
import com.example.usersappcompose.ui.screens.edit_user.EditEvent
import com.example.usersappcompose.ui.screens.edit_user.EditState
import javax.inject.Inject

class ShowUserUseCase @Inject constructor(private val databaseRepository: DatabaseRepository) :
    UseCase<EditEvent, EditState> {
    override fun canHandle(event: EditEvent): Boolean {
        return event is EditEvent.GetSavedUser
    }

    override suspend fun invoke(event: EditEvent, state: EditState): EditEvent {
        return ((event as? EditEvent.GetSavedUser)?.let {
            val savedUser = databaseRepository.getCurrentUser()
            if (savedUser != null) return EditEvent.ShowSavedUser(savedUser)
            else return EditEvent.Error("No info about current user")
        } ?: EditEvent.Error("Wrong event type : $event"))
    }
}