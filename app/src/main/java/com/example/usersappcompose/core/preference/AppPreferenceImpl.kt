package com.example.usersappcompose.core.preference

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject

class AppPreferenceImpl @Inject constructor(private val sharedPreferences: SharedPreferences) :
    AppPreference {

    override val currentUser: MutableSet<String>?
        get() = sharedPreferences.getStringSet(USER_KEY, null)

    override fun saveCurrentUser(
        firstName: String,
        lastName: String,
        phoneNumber: String,
        email: String,
        picture: String,
    ) {
        val stringSet = setOf(firstName, lastName, phoneNumber, email, picture)
        sharedPreferences.edit {
            putStringSet(USER_KEY, stringSet)
        }
    }

    companion object {
        private const val USER_KEY = "user_key"
    }
}