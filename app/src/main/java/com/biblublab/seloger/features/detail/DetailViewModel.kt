package com.biblublab.seloger.features.detail

import androidx.lifecycle.viewModelScope
import com.biblublab.domain.usecase.GetDetailUseCase
import com.biblublab.seloger.common.mvi.BaseViewModel
import com.biblublab.seloger.common.ui.UiState
import com.biblublab.seloger.features.EntityMapper
import kotlinx.coroutines.launch

class DetailViewModel(private val getDetailUseCase: GetDetailUseCase, private val entityMapper: EntityMapper) : BaseViewModel<DetailScreenState, DetailScreenEffect, DetailScreenAction>() {

    init {
        viewState = DetailScreenState(UiState.NotFetched)
    }

    override fun process(viewAction: DetailScreenAction) {
        super.process(viewAction)
        when(viewAction){
            is DetailScreenAction.GetDetail -> fetchDetail(viewAction.id)
        }
    }

    private fun fetchDetail(id : Int){
        viewModelScope.launch {
            getDetailUseCase(id).either({
                viewState = viewState.copy(uiState = UiState.Error(it))
            },
                {
                    viewState = viewState.copy(uiState = UiState.Fetched, realPropertyEntity = entityMapper.toRealPropertyEntity(it) )
                })
        }
    }
}