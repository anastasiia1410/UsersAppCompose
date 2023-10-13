package com.example.usersappcompose.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.usersappcompose.data.UsersPageSource
import com.example.usersappcompose.data.network.Api.Companion.DEFAULT_PAGE_SIZE
import com.example.usersappcompose.screens.entity.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val pagingSource: UsersPageSource) : ViewModel() {

    val pager: StateFlow<PagingData<User>> = Pager(
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
}