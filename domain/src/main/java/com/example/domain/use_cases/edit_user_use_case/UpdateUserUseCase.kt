package com.example.domain.use_cases.edit_user_use_case

import com.example.core.UseCase
import com.example.domain.entity.CURRENT_USER_ID
import com.example.domain.entity.User
import com.example.domain.repository.db_repository.DatabaseRepository

class UpdateUserUseCase(private val databaseRepository: DatabaseRepository) :
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
                return EditEvent.UpdateUserSaved
            } catch (e: Exception) {
                return EditEvent.Error("error $e")
            }
        } ?: EditEvent.Error("wrong event type: $event")
    }
}