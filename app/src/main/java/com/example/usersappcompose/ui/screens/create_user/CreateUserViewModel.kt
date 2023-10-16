package com.example.usersappcompose.ui.screens.create_user

import androidx.lifecycle.ViewModel
import com.example.usersappcompose.core.preference.AppPreference
import com.example.usersappcompose.ui.entity.CurrentUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateUserViewModel @Inject constructor(private val appPreference: AppPreference) : ViewModel() {
    fun saveCurrentUser(currentUser: CurrentUser) {
        appPreference.saveCurrentUser(
            currentUser.firstName,
            currentUser.lastName,
            currentUser.phoneNumber,
            currentUser.email,
            currentUser.picture
        )
    }
}