package com.example.usersappcompose.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.usersappcompose.core.BaseViewModel
import com.example.usersappcompose.data.db.DatabaseRepository
import com.example.usersappcompose.screens.entity.User
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject


class UserDetailViewModel @AssistedInject constructor(
    databaseRepository: DatabaseRepository,
    @Assisted uuid: String,
) :
    BaseViewModel<DetailEvents, DetailState>(
        useCases = listOf(GetDetailUserUseCase(databaseRepository)),
        reducer = DetailReducer(),
        initialState = User.initialUser()
    ) {

    init {
        getUser(uuid)
    }


    private fun getUser(uuid: String) {
        handleEvent(DetailEvents.GetUser(uuid))
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



