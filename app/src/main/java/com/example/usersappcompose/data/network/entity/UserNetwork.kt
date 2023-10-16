package com.example.usersappcompose.data.network.entity

import com.example.usersappcompose.ui.entity.User
import com.google.gson.annotations.SerializedName

data class UserNetwork(
    @SerializedName("gender")
    val gender: String,
    @SerializedName("name")
    val name: NameNetwork,
    @SerializedName("location")
    val location: LocationNetwork,
    @SerializedName("email")
    val email: String,
    @SerializedName("login")
    val login: LoginNetwork,
    @SerializedName("picture")
    val picture: PictureNetwork
)

fun UserNetwork.toUser(): User {
    return User(
        gender = gender,
        title = name.title,
        firstName = name.firstName,
        lastName = name.lastName,
        city = location.city,
        state = location.state,
        country = location.country,
        postCode = location.postCode,
        email = email,
        uuid = login.uuid,
        picture = picture.largePicture,
    )
}

data class NameNetwork(
    @SerializedName("title")
    val title: String,
    @SerializedName("first")
    val firstName: String,
    @SerializedName("last")
    val lastName: String,
)

data class LocationNetwork(
    @SerializedName("city")
    val city: String,
    @SerializedName("state")
    val state: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("postcode")
    val postCode: String,
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

