package com.example.usersappcompose.ui.screens.edit_user.use_case

import com.example.usersappcompose.core.UseCase
import com.example.usersappcompose.data.db.DatabaseRepository
import com.example.usersappcompose.ui.entity.CURRENT_USER_ID
import com.example.usersappcompose.ui.entity.User
import com.example.usersappcompose.ui.screens.edit_user.EditEvent
import com.example.usersappcompose.ui.screens.edit_user.EditState
import javax.inject.Inject

class UpdateUserUseCase @Inject constructor(private val databaseRepository: DatabaseRepository) :
    UseCase<EditEvent, EditState> {
    override fun canHandle(event: EditEvent): Boolean {
        return event is EditEvent.SaveUpdateUser
    }

    override suspend fun invoke(event: EditEvent, state: EditState): EditEvent {
        return ((event) as? EditEvent.SaveUpdateUser)?.let {
            try {
                val user = User(
                    uuid = CURRENT_USER_ID,
                    firstName = state.firstName,
                    lastName = state.lastName,
                    phoneNumber = state.phoneNumber,
                    email = state.email,
                    picture = state.picture,
                )
                databaseRepository.updateUserInfo(user)
                return EditEvent.None
            } catch (e: Exception) {
                return EditEvent.Error("error $e")
            }
        } ?: EditEvent.Error("wrong event type: $event")
    }
}