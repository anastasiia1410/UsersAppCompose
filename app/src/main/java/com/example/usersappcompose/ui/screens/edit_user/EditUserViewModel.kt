package com.example.usersappcompose.ui.screens.edit_user

import androidx.navigation.navOptions
import com.example.usersappcompose.core.BaseViewModel
import com.example.usersappcompose.core.Router
import com.example.usersappcompose.ui.screens.edit_user.use_case.GetUserUseCase
import com.example.usersappcompose.ui.screens.edit_user.use_case.UpdateUserUseCase
import com.example.usersappcompose.ui.screens.main.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
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

    fun changePicture(text: String) {
        handleEvent(EditEvent.ChangePicture(text))
    }

   private fun handleSavedUpdateUserEvent() {
        onNavigationRequested({ it is EditEvent.UpdateUserSaved }, {
            router.navigate(Screen.UsersContactScreen.route, navOptions {
                popUpTo(Screen.CreateUserScreen.route) { inclusive = true }
            })
        })
    }
}