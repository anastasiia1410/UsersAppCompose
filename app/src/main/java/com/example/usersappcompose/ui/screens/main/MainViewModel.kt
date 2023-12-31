package com.example.usersappcompose.ui.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.repository.db_repository.DatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val databaseRepository: DatabaseRepository) :
    ViewModel() {
    private val _startScreenFlow = MutableStateFlow(Screen.UsersContactScreen.route)
    val startScreenFlow: StateFlow<String>
        get() = _startScreenFlow.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean>
        get() = _isLoading

    init {
        checkCurrentUser()
    }

    private fun checkCurrentUser() {
        viewModelScope.launch(Dispatchers.IO) {
            val currentUser = databaseRepository.getUser()
            withContext(Dispatchers.Main) {
                if (currentUser == null) {
                    _startScreenFlow.value = Screen.CreateUserScreen.route
                } else {
                    _startScreenFlow.value = Screen.UsersContactScreen.route
                }
                _isLoading.value = true
            }
        }
    }
}

