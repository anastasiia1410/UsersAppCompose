package com.example.usersappcompose.ui.screens.add_contact

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.data.network.Api.Companion.DEFAULT_PAGE_SIZE
import com.example.data.page_source.UsersPageSource
import com.example.domain.entity.Category
import com.example.domain.entity.Contact
import com.example.domain.use_cases.add_contact.AddContactEvent
import com.example.domain.use_cases.add_contact.AddContactState
import com.example.domain.use_cases.add_contact.use_case.SaveToDbUseCase
import com.example.usersappcompose.core.BaseViewModel
import com.example.usersappcompose.core.Router
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class AddContactViewModel @Inject constructor(
    private val router: Router,
    private val pagingSource: UsersPageSource,
    saveToDbUseCase: SaveToDbUseCase,
) :
    BaseViewModel<AddContactEvent, AddContactState>(
        useCases = listOf(saveToDbUseCase),
        reducer = com.example.domain.use_cases.add_contact.AddContactReducer(),
        initialState = AddContactState(category = Category.ALL)
    ) {

    val pager: StateFlow<PagingData<Contact>> = Pager(
        config = PagingConfig(
            pageSize = DEFAULT_PAGE_SIZE,
            enablePlaceholders = false,
            initialLoadSize = DEFAULT_PAGE_SIZE * 3
        ),
        pagingSourceFactory = {
            pagingSource
        }, initialKey = 1
    ).flow.cachedIn(viewModelScope)
        .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

    init {
        handleUserSavedEvent()
    }

    fun saveContact(contact: Contact, category: Category) {
        handleEvent(AddContactEvent.SaveUserToContact(contact.copy(category = category)))
    }

    private fun handleUserSavedEvent() {
        doOnEvent(
            { it is AddContactEvent.UserSaved },
            { router.pop() })
    }
}
