package com.example.domain.use_cases.edit_user_use_case

import com.example.core.Reducer

class EditReducer : Reducer<EditEvent, EditState> {
    override fun reduce(event: EditEvent, state: EditState): EditState {
        return when (event) {
            is EditEvent.Error -> state
            EditEvent.GetSavedUser -> state
            is EditEvent.ShowSavedUser -> state.copy(
                firstName = event.firstName,
                lastName = event.lastName,
                phoneNumber = event.phoneNumber,
                email = event.email,
                picture = event.picture
            )
            is EditEvent.ChangeEmail -> state.copy(email = event.email)
            is EditEvent.ChangeFirstName -> state.copy(firstName = event.firstName)
            is EditEvent.ChangeLastName -> state.copy(lastName = event.lastName)
            is EditEvent.ChangePhoneNumber -> state.copy(phoneNumber = event.phoneNumber)
            is EditEvent.ChangePicture -> state.copy(picture = event.picture)
            EditEvent.SaveUpdateUser -> state
            EditEvent.UpdateUserSaved -> state
            EditEvent.None -> state
        }
    }
}