package com.example.usersappcompose.ui.screens.main

import androidx.lifecycle.ViewModel
import com.example.usersappcompose.core.preference.AppPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(appPreference: AppPreference) : ViewModel() {
    val currentUserFlow = MutableStateFlow(appPreference.currentUser)
}