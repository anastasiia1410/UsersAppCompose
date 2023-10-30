package com.example.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.entity.User

@Entity(tableName = "User")
data class UserDatabase(
    @PrimaryKey(autoGenerate = false)
    val uuid: String,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val email: String,
    val picture: String,
)

fun User.toUserDatabase(): UserDatabase {
    return UserDatabase(
        firstName = firstName,
        lastName = lastName,
        phoneNumber = phoneNumber,
        email = email,
        uuid = uuid,
        picture = picture,
    )
}

fun UserDatabase.toUser(): User {
    return User(
        firstName = firstName,
        lastName = lastName,
        phoneNumber = phoneNumber,
        email = email,
        uuid = uuid,
        picture = picture,
    )
}