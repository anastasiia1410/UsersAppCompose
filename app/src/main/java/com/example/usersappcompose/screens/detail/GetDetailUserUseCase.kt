package com.example.usersappcompose.screens.detail

import com.example.network_db.core.UseCase
import com.example.usersappcompose.data.db.DatabaseRepository
import javax.inject.Inject

class GetDetailUserUseCase @Inject constructor(private val databaseRepository: DatabaseRepository) :
    UseCase<DetailEvents, DetailState> {
    override fun canHandle(event: DetailEvents): Boolean {
        return event is DetailEvents.GetUser
    }

    override suspend fun invoke(event: DetailEvents, state: DetailState): DetailEvents {
        return ((event as? DetailEvents.GetUser)?.let {
            val user = databaseRepository.getUserById(event.uuid)
            return DetailEvents.ShowUser(user)
        } ?: DetailEvents.Error("Wrong event type : $event"))
    }
}