package com.biblublab.seloger.common.ui

sealed class UiState {
    object NotFetched : UiState()
    object Fetching : UiState()
    object Fetched : UiState()
    data class Error(val mess : String) : UiState()
}