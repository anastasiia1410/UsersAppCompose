package com.example.usersappcompose.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.Contact
import com.example.domain.use_cases.detail_user.DetailEvent
import com.example.domain.use_cases.detail_user.DetailReducer
import com.example.domain.use_cases.detail_user.DetailState
import com.example.domain.use_cases.detail_user.use_cases.DeleteContactUseCase
import com.example.domain.use_cases.detail_user.use_cases.GetDetailUserUseCase
import com.example.usersappcompose.core.BaseViewModel
import com.example.usersappcompose.core.Router
import com.example.usersappcompose.ui.screens.detail.ui_state.DetailUiState
import com.example.usersappcompose.ui.screens.detail.ui_state.toUiState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn


class UserDetailViewModel @AssistedInject constructor(
    private val router: Router,
    getDetailUserUseCase: GetDetailUserUseCase,
    deleteContactUseCase: DeleteContactUseCase,
    @Assisted uuid: String,
) :
    BaseViewModel<DetailEvent, DetailState>(
        useCases = listOf(
            getDetailUserUseCase,
            deleteContactUseCase
        ),
        reducer = DetailReducer(),
        initialState = Contact.initialContact()
    ) {
    val uiState: StateFlow<DetailUiState>
        get() = _state.map { it.toUiState() }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(1000),
                initialValue = DetailUiState.initialContactUi()

            )


    init {
        getUser(uuid)
        handleEventDeletedUser()
    }


    private fun getUser(uuid: String) {
        handleEvent(DetailEvent.GetUser(uuid))
    }

    fun deleteUser() {
        handleEvent(DetailEvent.DeleteUser)

    }

    private fun handleEventDeletedUser() {
        doOnEvent(
            filter = { it is DetailEvent.UserDeleted },
            onEvent = { router.pop() })
    }


    @AssistedFactory
    interface Factory {
        fun create(
            uuid: String,
        ): UserDetailViewModel
    }

    companion object {
        fun provideUserDetailViewModelFactory(
            factory: Factory,
            uuid: String,
        ): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return factory.create(uuid) as T
                }
            }
        }
    }
}



