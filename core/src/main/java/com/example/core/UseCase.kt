package com.example.core

interface UseCase<Event, State> {
    fun canHandle(event: Event): Boolean
    suspend fun invoke(event: Event, state: State): Event
}