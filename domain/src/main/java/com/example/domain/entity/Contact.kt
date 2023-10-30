package com.example.domain.entity

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
        fun initialUser(): com.example.domain.use_cases.detail_user_use_case.DetailState {
            return com.example.domain.use_cases.detail_user_use_case.DetailState(null)
        }
    }
}

