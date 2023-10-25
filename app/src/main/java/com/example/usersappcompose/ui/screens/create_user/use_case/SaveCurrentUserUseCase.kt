package com.example.usersappcompose.ui.screens.create_user.use_case

import com.example.usersappcompose.core.UseCase
import com.example.usersappcompose.data.db.DatabaseRepository
import com.example.usersappcompose.ui.entity.CURRENT_USER_ID
import com.example.usersappcompose.ui.entity.User
import com.example.usersappcompose.ui.screens.create_user.CreateUserEvent
import com.example.usersappcompose.ui.screens.create_user.CreateUserSate
import java.sql.SQLException
import javax.inject.Inject

class SaveCurrentUserUseCase @Inject constructor(private val databaseRepository: DatabaseRepository) :
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
                return CreateUserEvent.None
            } catch (e: SQLException) {
                return CreateUserEvent.Error("error $e")
            }

        } ?: CreateUserEvent.Error("Wrong event type : $event"))

    }
}