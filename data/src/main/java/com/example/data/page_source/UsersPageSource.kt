package com.example.data.page_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.network.Api
import com.example.data.network.entity.toContact
import com.example.domain.entity.Contact
import javax.inject.Inject

class UsersPageSource @Inject constructor(private val api: Api) :
    PagingSource<Int, Contact>() {
    override fun getRefreshKey(state: PagingState<Int, Contact>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Contact> {
        val page = params.key ?: 1
        val results: Int = params.loadSize
        val users = api.getContacts(page, results).contactList.map { it.toContact() }
        val nextKey = if (users.size < results) null else page.plus(1)
        val prevKey = if (page == 1) null else page.minus(1)
        return LoadResult.Page(users, prevKey, nextKey)
    }
}

