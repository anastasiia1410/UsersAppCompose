package com.example.core

interface Reducer<Event, State> {
    fun reduce(event: Event, state: State): State
}