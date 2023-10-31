package com.example.usersappcompose.ui.screens.create_user

import androidx.lifecycle.viewModelScope
import androidx.navigation.navOptions
import com.example.domain.use_cases.create_user.CreateUserEvent
import com.example.domain.use_cases.create_user.CreateUserSate
import com.example.domain.use_cases.create_user.CurrentUserReducer
import com.example.domain.use_cases.create_user.use_case.SaveCurrentUserUseCase
import com.example.usersappcompose.core.BaseViewModel
import com.example.usersappcompose.core.Router
import com.example.usersappcompose.ui.screens.create_user.ui_state.CreateUserUiState
import com.example.usersappcompose.ui.screens.create_user.ui_state.toUiState
import com.example.usersappcompose.ui.screens.main.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CreateUserViewModel @Inject constructor(
    private val router: Router,
    saveCurrentUserUseCase: SaveCurrentUserUseCase,
) :
    BaseViewModel<CreateUserEvent, CreateUserSate>(
        useCases = listOf(
            saveCurrentUserUseCase
        ),
        reducer = CurrentUserReducer(),
        initialState = CreateUserSate(
            "",
            "",
            "",
            "",
            ""
        )
    ) {


    val uiState: StateFlow<CreateUserUiState>
        get() = _state.map { it.toUiState() }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = CreateUserUiState("", "", "", "", "")
            )

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