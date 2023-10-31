package com.example.domain.use_cases.detail_user

import com.example.core.Reducer

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