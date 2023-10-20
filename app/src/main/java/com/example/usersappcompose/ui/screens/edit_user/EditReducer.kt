package com.example.usersappcompose.ui.screens.edit_user

import com.example.usersappcompose.core.Reducer

class EditReducer : Reducer<EditEvent, EditState> {
    override fun reduce(event: EditEvent, state: EditState): EditState {
        return when (event) {
            is EditEvent.Error -> state
            EditEvent.GetSavedUser -> state
            is EditEvent.RefactorSavedUser -> state.copy(user = event.user)
            is EditEvent.ShowSavedUser -> state.copy(user = event.user)
        }
    }
}