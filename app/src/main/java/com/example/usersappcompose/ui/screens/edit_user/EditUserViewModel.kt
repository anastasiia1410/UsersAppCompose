package com.example.usersappcompose.ui.screens.edit_user

import com.example.usersappcompose.core.BaseViewModel
import com.example.usersappcompose.data.db.DatabaseRepository
import com.example.usersappcompose.ui.entity.User
import com.example.usersappcompose.ui.screens.edit_user.use_case.RefactorUserUseCase
import com.example.usersappcompose.ui.screens.edit_user.use_case.ShowUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class EditUserViewModel @Inject constructor(databaseRepository: DatabaseRepository) :
    BaseViewModel<EditEvent, EditState>(
        useCases = listOf(ShowUserUseCase(databaseRepository), RefactorUserUseCase(databaseRepository)),
        reducer = EditReducer(),
        initialState = User.initialEditUser()
    ) {

    val isLoaded = MutableStateFlow(false)

    init {
        showUserInfo()
    }

    private fun showUserInfo() {
        handleEvent(EditEvent.GetSavedUser)
        isLoaded.value = true
    }

    fun saveEditUser(user: User) {
        handleEvent(EditEvent.RefactorSavedUser(user))
    }
}