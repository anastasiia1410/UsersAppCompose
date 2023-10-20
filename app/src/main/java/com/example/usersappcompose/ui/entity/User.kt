package com.example.usersappcompose.ui.entity

import com.example.usersappcompose.ui.screens.add_contact.AddContactState
import com.example.usersappcompose.ui.screens.create_user.CurrentUserSate
import com.example.usersappcompose.ui.screens.detail.DetailState
import com.example.usersappcompose.ui.screens.edit_user.EditState


data class User(
    val firstName: String,
    val lastName: String,
    val phoneNumber: String?,
    val email: String,
    val uuid: String,
    val picture: String,
    val category: String?,
) {

    fun doesMatchSearchQuery(query : String) : Boolean{
        val matchingCombinations = listOf(
            "$firstName$lastName",
            "$firstName $lastName",
            "${firstName.first()} ${lastName.first()}",

        )
        return matchingCombinations.any {
            it.contains(query, ignoreCase = true)
        }
    }
    companion object {
        fun initialUser(): DetailState {
            return DetailState(null)
        }

        fun initialCurrentUser(): CurrentUserSate {
            return CurrentUserSate(User("", "", "", "", "", "", ""))
        }

        fun initialEditUser() : EditState{
            return EditState(null)
        }

        fun initialSaveContact() : AddContactState{
            return AddContactState(null)
        }
    }
}

enum class Category{
    ALL, FAMILY, FRIENDS, WORK
}