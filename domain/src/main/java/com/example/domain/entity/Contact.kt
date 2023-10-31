package com.example.domain.entity

import com.example.domain.use_cases.detail_user.DetailState

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
        fun initialContact(): DetailState {
            return DetailState(null)
        }
    }
}

