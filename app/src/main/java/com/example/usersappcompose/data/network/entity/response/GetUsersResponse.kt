package com.example.usersappcompose.data.network.entity.response

import com.example.usersappcompose.data.network.entity.UserNetwork
import com.google.gson.annotations.SerializedName

data class GetUsersResponse(@SerializedName("results") val userList: List<UserNetwork>)