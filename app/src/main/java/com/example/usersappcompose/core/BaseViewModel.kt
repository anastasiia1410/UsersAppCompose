package com.example.usersappcompose.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<Event, State>(
    private val useCases: List<UseCase<Event, State>>,
    private val reducer: Reducer<Event, State>,
    initialState: State,
) : ViewModel() {


    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<State>
        get() = _state.asStateFlow()

    protected fun handleEvent(event: Event) {
        val newState = reducer.reduce(event, state.value)
        _state.value = newState
        useCases.filter { it.canHandle(event) }.forEach {
            viewModelScope.launch(Dispatchers.IO) {
                val result = it.invoke(event, newState)
                handleEvent(result)
            }
        }
    }
}