package com.example.domain.use_cases.edit_user_use_case

import com.example.core.UseCase
import com.example.domain.repository.db_repository.DatabaseRepository

class GetUserUseCase(private val databaseRepository: DatabaseRepository) :
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