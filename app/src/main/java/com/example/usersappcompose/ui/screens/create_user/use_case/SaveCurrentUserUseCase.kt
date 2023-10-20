package com.example.usersappcompose.ui.screens.create_user.use_case

import com.example.usersappcompose.core.UseCase
import com.example.usersappcompose.data.db.DatabaseRepository
import com.example.usersappcompose.ui.entity.User
import com.example.usersappcompose.ui.screens.create_user.CurrentUserEvent
import com.example.usersappcompose.ui.screens.create_user.CurrentUserSate
import javax.inject.Inject

class SaveCurrentUserUseCase @Inject constructor(private val databaseRepository: DatabaseRepository) :
    UseCase<CurrentUserEvent, CurrentUserSate> {
    override fun canHandle(event: CurrentUserEvent): Boolean {
        return event is CurrentUserEvent.AddUser
    }

    override suspend fun invoke(event: CurrentUserEvent, state: CurrentUserSate): CurrentUserEvent {
        return ((event as? CurrentUserEvent.AddUser)?.let {
            val user = User(
                uuid = "1",
                firstName = event.firstName,
                lastName = event.lastName,
                phoneNumber = event.phoneNumber,
                email = event.email,
                picture = event.picture,
                category = null
            )
            databaseRepository.insertUser(user)

            return CurrentUserEvent.SaveCurrentUser(user)


        } ?: CurrentUserEvent.Error("Wrong event type : $event"))

    }
}