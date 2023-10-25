package com.example.usersappcompose.ui.screens.detail.use_case

import com.example.usersappcompose.core.UseCase
import com.example.usersappcompose.data.db.DatabaseRepository
import com.example.usersappcompose.ui.screens.detail.DetailEvent
import com.example.usersappcompose.ui.screens.detail.DetailState
import javax.inject.Inject

class GetDetailUserUseCase @Inject constructor(private val databaseRepository: DatabaseRepository) :
    UseCase<DetailEvent, DetailState> {
    override fun canHandle(event: DetailEvent): Boolean {
        return event is DetailEvent.GetUser
    }

    override suspend fun invoke(event: DetailEvent, state: DetailState): DetailEvent {
        return ((event as? DetailEvent.GetUser)?.let {
            try {
                val user = databaseRepository.getContactById(event.uuid)
                user?.let {
                    return DetailEvent.ShowUser(user)
                }
            } catch (e: NullPointerException) {
                return DetailEvent.Error("user isn`t found")
            }
        } ?: DetailEvent.Error("Wrong event type : $event"))
    }
}