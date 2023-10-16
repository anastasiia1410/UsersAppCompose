package com.example.usersappcompose.ui.screens.detail.use_case

import com.example.network_db.core.UseCase
import com.example.usersappcompose.data.db.DatabaseRepository
import com.example.usersappcompose.ui.screens.detail.DetailEvents
import com.example.usersappcompose.ui.screens.detail.DetailState
import javax.inject.Inject

class GetDetailUserUseCase @Inject constructor(private val databaseRepository: DatabaseRepository) :
    UseCase<DetailEvents, DetailState> {
    override fun canHandle(event: DetailEvents): Boolean {
        return event is DetailEvents.GetUser
    }

    override suspend fun invoke(event: DetailEvents, state: DetailState): DetailEvents {
        return ((event as? DetailEvents.GetUser)?.let {
            kotlin.runCatching {
                databaseRepository.getUserById(event.uuid)
            }.onSuccess { user ->
                return DetailEvents.ShowUser(user)
            }.onFailure { throwable ->
                return DetailEvents.Error(throwable.message!!)
            }
            return DetailEvents.Error("An unexpected error occurred")
        } ?: DetailEvents.Error("Wrong event type : $event"))
    }
}