package com.example.usersappcompose.ui.screens.edit_user

import androidx.lifecycle.viewModelScope
import androidx.navigation.navOptions
import com.example.domain.use_cases.edit_user.EditEvent
import com.example.domain.use_cases.edit_user.EditReducer
import com.example.domain.use_cases.edit_user.EditState
import com.example.domain.use_cases.edit_user.use_cases.GetUserUseCase
import com.example.domain.use_cases.edit_user.use_cases.UpdateUserUseCase
import com.example.usersappcompose.core.BaseViewModel
import com.example.usersappcompose.core.Router
import com.example.usersappcompose.ui.screens.edit_user.ui_state.EditUserUiState
import com.example.usersappcompose.ui.screens.edit_user.ui_state.toUiState
import com.example.usersappcompose.ui.screens.main.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class EditUserViewModel @Inject constructor(
    private val router: Router,
    getUserUseCase: GetUserUseCase,
    updateUserUseCase: UpdateUserUseCase,
) :
    BaseViewModel<EditEvent, EditState>(
        useCases = listOf(
            getUserUseCase,
            updateUserUseCase
        ),
        reducer = EditReducer(),
        initialState = EditState("", "", "", "", "")
    ) {

    val uiState = _state.map { it.toUiState()}
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(2000),
            initialValue = EditUserUiState("", "", "", "", "")
        )


    init {
        showUserInfo()
        handleSavedUpdateUserEvent()
    }

    private fun showUserInfo() {
        handleEvent(EditEvent.GetSavedUser)

    }

    fun updateUser() {
        handleEvent(EditEvent.SaveUpdateUser)
    }

    fun changeFirstName(text: String) {
        handleEvent(EditEvent.ChangeFirstName(text))
    }

    fun changeLastName(text: String) {
        handleEvent(EditEvent.ChangeLastName(text))
    }

    fun changePhoneNumber(text: String) {
        handleEvent(EditEvent.ChangePhoneNumber(text))
    }

    fun changeEmail(text: String) {
        handleEvent(EditEvent.ChangeEmail(text))
    }

   private fun handleSavedUpdateUserEvent() {
        doOnEvent({ it is EditEvent.UpdateUserSaved }, {
            router.navigate(Screen.UsersContactScreen.route, navOptions {
                popUpTo(Screen.CreateUserScreen.route) { inclusive = true }
            })
        })
    }
}