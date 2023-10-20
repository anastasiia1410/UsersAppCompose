package com.example.usersappcompose.ui.screens.detail.use_case

import com.example.usersappcompose.core.UseCase
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
            try {
                val user = databaseRepository.getUserById(event.uuid)
                return DetailEvents.ShowUser(user)
            } catch (t: Throwable) {
                return DetailEvents.Error(t.message!!)
            }
        } ?: DetailEvents.Error("Wrong event type : $event"))
    }
}