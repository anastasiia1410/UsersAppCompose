package com.example.usersappcompose.ui.screens.create_user

import androidx.navigation.navOptions
import com.example.domain.use_cases.create_user_use_case.CreateUserEvent
import com.example.domain.use_cases.create_user_use_case.CreateUserSate
import com.example.usersappcompose.core.BaseViewModel
import com.example.usersappcompose.core.Router
import com.example.usersappcompose.ui.screens.main.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateUserViewModel @Inject constructor(
    private val router: Router,
    saveCurrentUserUseCase: com.example.domain.use_cases.create_user_use_case.SaveCurrentUserUseCase,
) :
    BaseViewModel<CreateUserEvent, CreateUserSate>(
        useCases = listOf(
            saveCurrentUserUseCase
        ),
        reducer = com.example.domain.use_cases.create_user_use_case.CurrentUserReducer(),
        initialState = CreateUserSate(
            "",
            "",
            "",
            "",
            ""
        )
    ) {

    init {
        handleUserSavedEvent()
    }

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

    private fun handleUserSavedEvent() {
        doOnEvent(
            filter = { it is CreateUserEvent.UserSaved },
            onEvent = {
                router.navigate(route = Screen.UsersContactScreen.route, navOptions {
                    popUpTo(Screen.CreateUserScreen.route) { inclusive = true }
                })
            })
    }
}