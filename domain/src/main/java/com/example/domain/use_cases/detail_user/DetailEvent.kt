package com.example.domain.use_cases.detail_user

import com.example.domain.entity.Contact


sealed class DetailEvent {
    data class GetUser(val uuid: String) : DetailEvent()
    data class ShowUser(val contact: Contact) : DetailEvent()
    object DeleteUser : DetailEvent()
    object UserDeleted : DetailEvent()
    object None : DetailEvent()
    data class Error(val error: String) : DetailEvent()
}
