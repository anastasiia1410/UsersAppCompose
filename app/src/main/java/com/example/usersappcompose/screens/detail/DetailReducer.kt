package com.example.usersappcompose.screens.detail

import com.example.usersappcompose.core.Reducer

class DetailReducer : Reducer<DetailEvents, DetailState> {
    override fun reduce(event: DetailEvents, state: DetailState): DetailState {
        return when (event) {
            is DetailEvents.GetUser, is DetailEvents.Error -> state
            is DetailEvents.ShowUser -> state.copy(user = event.user)
        }
    }
}