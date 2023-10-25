package com.example.usersappcompose.ui.screens.add_contact

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.usersappcompose.core.BaseViewModel
import com.example.usersappcompose.data.UsersPageSource
import com.example.usersappcompose.data.network.Api
import com.example.usersappcompose.ui.entity.Category
import com.example.usersappcompose.ui.entity.Contact
import com.example.usersappcompose.ui.screens.add_contact.use_case.SaveToDbUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class AddContactViewModel @Inject constructor(
    private val pagingSource: UsersPageSource,
    saveToDbUseCase: SaveToDbUseCase,
) :
    BaseViewModel<AddContactEvent, AddContactState>(
        useCases = listOf(saveToDbUseCase),
        reducer = AddContactReducer(),
        initialState = AddContactState(category = Category.ALL)
    ) {

    val pager: StateFlow<PagingData<Contact>> = Pager(
        config = PagingConfig(
            pageSize = Api.DEFAULT_PAGE_SIZE,
            enablePlaceholders = false,
            initialLoadSize = Api.DEFAULT_PAGE_SIZE * 3
        ),
        pagingSourceFactory = {
            pagingSource
        }, initialKey = 1
    ).flow.cachedIn(viewModelScope)
        .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())


    fun saveContact(contact: Contact, category: Category) {
        handleEvent(AddContactEvent.SaveUserToContact(contact.copy(category = category)))
    }
}
