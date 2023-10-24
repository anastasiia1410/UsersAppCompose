package com.example.usersappcompose.ui.screens.create_user

import com.example.usersappcompose.core.BaseViewModel
import com.example.usersappcompose.data.db.DatabaseRepository
import com.example.usersappcompose.ui.screens.create_user.use_case.SaveCurrentUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateUserViewModel @Inject constructor(databaseRepository: DatabaseRepository) :
    BaseViewModel<CreateUserEvent, CreateUserSate>(
        useCases = listOf(
            SaveCurrentUserUseCase(databaseRepository)
        ),
        reducer = CurrentUserReducer(),
        initialState = CreateUserSate("", "", "", "", "")
    ) {

    fun saveCurrentUser() {
        handleEvent(CreateUserEvent.ReceiveUser)
    }

    fun changeFirstName(text: String) {
        handleEvent(CreateUserEvent.SetFirstName(text))
    }

    fun changeLastName(text: String) {
        handleEvent(CreateUserEvent.SetLastName(text))
    }

    fun changePhoneNumber(text: String) {
        handleEvent(CreateUserEvent.SetPhoneNumber(text))
    }

    fun changeEmail(text: String) {
        handleEvent(CreateUserEvent.SetEmail(text))
    }

    fun changePicture(text: String) {
        handleEvent(CreateUserEvent.SetPicture(text))
    }
}