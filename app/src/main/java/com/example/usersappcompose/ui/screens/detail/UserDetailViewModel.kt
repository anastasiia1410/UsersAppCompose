package com.example.usersappcompose.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.usersappcompose.core.BaseViewModel
import com.example.usersappcompose.core.Router
import com.example.usersappcompose.ui.entity.Contact
import com.example.usersappcompose.ui.screens.detail.use_case.DeleteContactUseCase
import com.example.usersappcompose.ui.screens.detail.use_case.GetDetailUserUseCase
import com.example.usersappcompose.ui.screens.main.Screen
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject


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
        initialState = Contact.initialUser()
    ) {

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
        onNavigationRequested(
            filter = { it is DetailEvent.UserDeleted },
            onEvent = { router.navigate(Screen.UsersContactScreen.route) })
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



