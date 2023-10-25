package com.example.usersappcompose.data.network

import com.example.usersappcompose.data.network.entity.response.GetUsersResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("api/")
    suspend fun getContacts(
        @Query("page") page: Int = 1,
        @Query("results") results: Int = DEFAULT_PAGE_SIZE,
    ): GetUsersResponse

    companion object {
        const val DEFAULT_PAGE_SIZE = 20
    }
}