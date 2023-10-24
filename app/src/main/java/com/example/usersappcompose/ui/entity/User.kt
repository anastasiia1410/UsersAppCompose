package com.example.usersappcompose.ui.entity

import com.example.usersappcompose.ui.screens.detail.DetailState

const val CURRENT_USER_ID = "1"
data class User(
    val firstName: String,
    val lastName: String,
    val phoneNumber: String?,
    val email: String,
    val uuid: String,
    val picture: String,
    val category: String?,
) {

    fun doesMatchSearchQuery(query: String): Boolean {
        val matchingCombinations = listOf(firstName, lastName)
        return matchingCombinations.any {
            it.contains(query, ignoreCase = true)
        }
    }

    companion object {
        fun initialUser(): DetailState {
            return DetailState(null)
        }
    }
}

