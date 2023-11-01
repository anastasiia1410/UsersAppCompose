package com.example.usersappcompose.ui.ui_models

import com.example.domain.entity.Category
import com.example.domain.entity.Contact

data class ContactUiModel(
    val firstName: String,
    val lastName: String,
    val email: String,
    val uuid: String,
    val picture: String,
    val category: Category,
)

fun Contact.toContactUiModel(): ContactUiModel {
    return ContactUiModel(
        firstName = firstName,
        lastName = lastName,
        email = email,
        uuid = uuid,
        picture = picture,
        category = category
    )
}

fun ContactUiModel.toContact(): Contact {
    return Contact(
        firstName = firstName,
        lastName = lastName,
        email = email,
        uuid = uuid,
        picture = picture,
        category = category
    )
}