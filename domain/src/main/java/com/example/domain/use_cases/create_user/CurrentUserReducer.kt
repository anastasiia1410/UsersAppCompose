package com.example.domain.use_cases.create_user

import com.example.core.Reducer

class CurrentUserReducer : Reducer<CreateUserEvent, CreateUserSate> {
    override fun reduce(event: CreateUserEvent, state: CreateUserSate): CreateUserSate {
        return when (event) {
            is CreateUserEvent.SetFirstName -> state.copy(firstName = event.firstName)
            is CreateUserEvent.SetLastName -> state.copy(lastName = event.lastName)
            is CreateUserEvent.SetPhoneNumber -> state.copy(phoneNumber = event.phoneNumber)
            is CreateUserEvent.SetEmail -> state.copy(email = event.email)
            is CreateUserEvent.SetPicture -> state.copy(picture = event.picture)
            is CreateUserEvent.Error -> state
            CreateUserEvent.ReceiveUser -> state
            CreateUserEvent.UserSaved -> state
            CreateUserEvent.None -> state
        }
    }
}