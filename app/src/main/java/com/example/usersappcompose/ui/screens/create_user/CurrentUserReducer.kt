package com.example.usersappcompose.ui.screens.create_user

import com.example.usersappcompose.core.Reducer

class CurrentUserReducer : Reducer<CurrentUserEvent, CurrentUserSate> {
    override fun reduce(event: CurrentUserEvent, state: CurrentUserSate): CurrentUserSate {
        return when (event) {
            is CurrentUserEvent.AddUser -> state
            is CurrentUserEvent.Error -> state
            is CurrentUserEvent.SaveCurrentUser -> state.copy(user = event.currentUser)
        }
    }
}