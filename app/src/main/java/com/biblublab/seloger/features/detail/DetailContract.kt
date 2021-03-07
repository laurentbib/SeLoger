package com.biblublab.seloger.features.detail

import com.biblublab.seloger.common.ui.UiState
import com.biblublab.seloger.features.RealPropertyEntity

data class DetailScreenState(val uiState: UiState, val realPropertyEntity: RealPropertyEntity? = null)

sealed class DetailScreenEffect{
    object BackToMain : DetailScreenEffect()
}

sealed class DetailScreenAction{
    data class GetDetail(val id : Int) : DetailScreenAction()
}