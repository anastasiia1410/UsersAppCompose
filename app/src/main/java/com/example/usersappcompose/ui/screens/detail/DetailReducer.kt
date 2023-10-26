package com.example.usersappcompose.ui.screens.detail

import com.example.usersappcompose.core.Reducer

class DetailReducer : Reducer<DetailEvent, DetailState> {
    override fun reduce(event: DetailEvent, state: DetailState): DetailState {
        return when (event) {
            is DetailEvent.GetUser, is DetailEvent.Error -> state
            is DetailEvent.ShowUser -> state.copy(contact = event.contact)
            is DetailEvent.DeleteUser -> state
            DetailEvent.UserDeleted -> state
            DetailEvent.None -> state

        }
    }
}