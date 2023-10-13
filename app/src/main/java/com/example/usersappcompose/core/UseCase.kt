package com.example.network_db.core

interface UseCase<Event, State> {
    fun canHandle(event: Event): Boolean
    suspend fun invoke(event: Event, state: State): Event
}