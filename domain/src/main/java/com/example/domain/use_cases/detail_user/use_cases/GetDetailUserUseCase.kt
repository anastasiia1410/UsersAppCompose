package com.example.domain.use_cases.detail_user.use_cases

import com.example.core.UseCase
import com.example.domain.repository.db_repository.DatabaseRepository
import com.example.domain.use_cases.detail_user.DetailEvent
import com.example.domain.use_cases.detail_user.DetailState

class GetDetailUserUseCase(private val databaseRepository: DatabaseRepository) :
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