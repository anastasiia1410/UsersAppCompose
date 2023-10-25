package com.example.usersappcompose.ui.screens.edit_user.use_case

import com.example.usersappcompose.core.UseCase
import com.example.usersappcompose.data.db.DatabaseRepository
import com.example.usersappcompose.ui.screens.edit_user.EditEvent
import com.example.usersappcompose.ui.screens.edit_user.EditState
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val databaseRepository: DatabaseRepository) :
    UseCase<EditEvent, EditState> {
    override fun canHandle(event: EditEvent): Boolean {
        return event is EditEvent.GetSavedUser
    }

    override suspend fun invoke(event: EditEvent, state: EditState): EditEvent {
        return ((event as? EditEvent.GetSavedUser)?.let {
            try {
                val savedUser = databaseRepository.getUser()
                savedUser?.let { user ->
                    EditEvent.ShowSavedUser(
                        firstName = user.firstName,
                        lastName = user.lastName,
                        phoneNumber = user.phoneNumber,
                        email = user.email,
                        picture = user.picture,
                    )
                }
            } catch (e: Exception) {
                return EditEvent.Error("error $e")
            }
        } ?: EditEvent.Error("Wrong event type : $event"))
    }
}