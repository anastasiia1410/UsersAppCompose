package com.example.data.network.entity.response

import com.example.data.network.entity.ContactNetwork
import com.google.gson.annotations.SerializedName

data class GetUsersResponse(@SerializedName("results") val contactList: List<ContactNetwork>)