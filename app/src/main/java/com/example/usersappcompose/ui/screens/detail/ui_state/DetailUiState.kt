package com.example.usersappcompose.ui.screens.detail.ui_state

import com.example.domain.use_cases.detail_user.DetailState
import com.example.usersappcompose.ui.ui_models.ContactUiModel
import com.example.usersappcompose.ui.ui_models.toContactUiModel

class DetailUiState(val uiContact: ContactUiModel?){

    companion object{
        fun initialContactUi() : DetailUiState{
            return DetailUiState(null)
        }
    }
}

fun DetailState.toUiState() : DetailUiState {
    return DetailUiState(
        uiContact = this.contact?.toContactUiModel()
    )
}