package com.example.usersappcompose.ui.screens.create_user

import com.example.usersappcompose.core.BaseViewModel
import com.example.usersappcompose.data.db.DatabaseRepository
import com.example.usersappcompose.ui.entity.User
import com.example.usersappcompose.ui.screens.create_user.use_case.SaveCurrentUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateUserViewModel @Inject constructor(databaseRepository: DatabaseRepository) :
    BaseViewModel<CurrentUserEvent, CurrentUserSate>(
        useCases = listOf(SaveCurrentUserUseCase(databaseRepository)),
        reducer = CurrentUserReducer(),
        initialState = User.initialCurrentUser()
    ) {

    fun saveCurrentUser(user: User) {
        handleEvent(
            CurrentUserEvent.AddUser(
                firstName = user.firstName,
                lastName = user.lastName,
                phoneNumber = user.phoneNumber!!,
                email = user.email,
                picture = user.picture
            )
        )
    }
}