package com.example.usersappcompose.ui.screens.edit_user.use_case

import com.example.usersappcompose.core.UseCase
import com.example.usersappcompose.data.db.DatabaseRepository
import com.example.usersappcompose.ui.screens.edit_user.EditEvent
import com.example.usersappcompose.ui.screens.edit_user.EditState
import javax.inject.Inject

class RefactorUserUseCase @Inject constructor(private val databaseRepository: DatabaseRepository) :
    UseCase<EditEvent, EditState> {
    override fun canHandle(event: EditEvent): Boolean {
        return event is EditEvent.RefactorSavedUser
    }

    override suspend fun invoke(event: EditEvent, state: EditState): EditEvent {
        return ((event) as? EditEvent.RefactorSavedUser)?.let {
            databaseRepository.updateUserInfo(event.user)
            return EditEvent.GetSavedUser
        } ?: EditEvent.Error("wrong event type: $event")
    }
}