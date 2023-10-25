package com.example.usersappcompose.ui.entity

import com.example.usersappcompose.ui.screens.detail.DetailState

data class Contact(
    val firstName: String,
    val lastName: String,
    val email: String,
    val uuid: String,
    val picture: String,
    val category: Category,
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

