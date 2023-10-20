package com.example.usersappcompose.ui.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usersappcompose.data.db.DatabaseRepository
import com.example.usersappcompose.ui.entity.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class ContactUsersViewModel @Inject constructor(private val databaseRepository: DatabaseRepository) :
    ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText: StateFlow<String>
        get() = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching: StateFlow<Boolean>
        get() = _isSearching.asStateFlow()

    private val _usersFlow = MutableStateFlow<List<User>?>(null)

    val users = searchText
        .debounce(1000L)
        .onEach { _isSearching.update { true } }
        .combine(_usersFlow) { text, users ->
            if (text.isBlank()) {
                users
            } else {
                delay(2000L)
                users?.filter {
                    it.doesMatchSearchQuery(text)
                }
            }
        }
        .onEach { _isSearching.update { false } }
        .stateIn(
            viewModelScope, SharingStarted.WhileSubscribed(5000),
            _usersFlow.value
        )

    init {
        getContacts()
    }

    private fun getContacts() {
        viewModelScope.launch {
            val userList = databaseRepository.getUsers()
            _usersFlow.value = userList
        }
    }

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }
}