package com.example.usersappcompose.data.network.entity.response

import com.example.usersappcompose.data.network.entity.ContactNetwork
import com.google.gson.annotations.SerializedName

data class GetUsersResponse(@SerializedName("results") val contactList: List<ContactNetwork>)