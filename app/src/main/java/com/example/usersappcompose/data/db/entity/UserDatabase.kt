package com.example.usersappcompose.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.usersappcompose.screens.entity.User

@Entity(tableName = "User")
data class UserDatabase(
    @PrimaryKey(false)
    val uuid: String,
    val gender: String,
    val title: String,
    val firstName: String,
    val lastName: String,
    val city: String,
    val state: String,
    val country: String,
    val postCode: String,
    val email: String,
    val picture: String,
)

fun User.toUserDatabase(): UserDatabase {
    return UserDatabase(
        gender = gender,
        title = title,
        firstName = firstName,
        lastName = lastName,
        city = city,
        state = state,
        country = country,
        postCode = postCode,
        email = email,
        uuid = uuid,
        picture = picture
    )
}

fun UserDatabase.toUser(): User {
    return User(
        gender = gender,
        title = title,
        firstName = firstName,
        lastName = lastName,
        city = city,
        state = state,
        country = country,
        postCode = postCode,
        email = email,
        uuid = uuid,
        picture = picture
    )
}

