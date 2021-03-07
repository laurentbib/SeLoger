package com.biblublab.seloger.features.main

import com.biblublab.seloger.common.ui.UiState
import com.biblublab.seloger.databinding.MainItemViewBinding
import com.biblublab.seloger.features.RealPropertyEntity

data class MainScreenState(val uiState: UiState, val realPropertyList : List<RealPropertyEntity>)

sealed class MainScreenEffect{
    data class OpenDetail(val binding : MainItemViewBinding, val realPropertyEntity: RealPropertyEntity) : MainScreenEffect()
}

sealed class MainScreenAction{
    object FetchData : MainScreenAction()
    data class OnClickItem(val binding : MainItemViewBinding, val realPropertyEntity: RealPropertyEntity) : MainScreenAction()
}