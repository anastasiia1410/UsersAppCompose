package com.example.data.network.entity

import com.example.domain.entity.Category
import com.example.domain.entity.Contact
import com.google.gson.annotations.SerializedName

data class ContactNetwork(
    @SerializedName("name")
    val name: NameNetwork,
    @SerializedName("email")
    val email: String,
    @SerializedName("login")
    val login: LoginNetwork,
    @SerializedName("picture")
    val picture: PictureNetwork,
)

fun ContactNetwork.toContact(): Contact {
    return Contact(
        firstName = name.firstName,
        lastName = name.lastName,
        email = email,
        uuid = login.uuid,
        picture = picture.largePicture,
        category = Category.ALL,
    )
}

data class NameNetwork(
    @SerializedName("first")
    val firstName: String,
    @SerializedName("last")
    val lastName: String,
)

data class LoginNetwork(@SerializedName("uuid") val uuid: String)

data class PictureNetwork(
    @SerializedName("large")
    val largePicture: String,
    @SerializedName("medium")
    val mediumPicture: String,
    @SerializedName("thumbnail")
    val thumbnailPicture: String,
)

