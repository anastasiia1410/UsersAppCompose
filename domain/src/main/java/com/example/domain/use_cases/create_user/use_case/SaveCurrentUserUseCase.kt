package com.example.domain.use_cases.create_user.use_case

import com.example.core.UseCase
import com.example.domain.entity.CURRENT_USER_ID
import com.example.domain.entity.User
import com.example.domain.repository.db_repository.DatabaseRepository
import com.example.domain.use_cases.create_user.CreateUserEvent
import com.example.domain.use_cases.create_user.CreateUserSate
import java.sql.SQLException

class SaveCurrentUserUseCase(private val databaseRepository: DatabaseRepository) :
    UseCase<CreateUserEvent, CreateUserSate> {
    override fun canHandle(event: CreateUserEvent): Boolean {
        return event is CreateUserEvent.ReceiveUser
    }

    override suspend fun invoke(event: CreateUserEvent, state: CreateUserSate): CreateUserEvent {
        return ((event as? CreateUserEvent.ReceiveUser)?.let {
            val user = User(
                uuid = CURRENT_USER_ID,
                firstName = state.firstName,
                lastName = state.lastName,
                phoneNumber = state.phoneNumber,
                email = state.email,
                picture = state.picture,
            )
            try {
                databaseRepository.insertUser(user)
                return CreateUserEvent.UserSaved
            } catch (e: SQLException) {
                return CreateUserEvent.Error("error $e")
            }

        } ?: CreateUserEvent.Error("Wrong event type : $event"))
    }
}