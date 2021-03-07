package com.biblublab.seloger.features.main

import androidx.lifecycle.viewModelScope
import com.biblublab.domain.model.RealProperty
import com.biblublab.domain.usecase.FetchDataUseCase
import com.biblublab.seloger.common.mvi.BaseViewModel
import com.biblublab.seloger.common.ui.UiState
import com.biblublab.seloger.features.EntityMapper
import com.biblublab.seloger.features.RealPropertyEntity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel(private val fetchDataUseCase: FetchDataUseCase, private val entityMapper: EntityMapper) : BaseViewModel<MainScreenState, MainScreenEffect, MainScreenAction>() {

    init {
        viewState = MainScreenState(UiState.NotFetched, realPropertyList = emptyList())
    }

    override fun process(viewAction: MainScreenAction) {
        super.process(viewAction)
        when(viewAction){
            MainScreenAction.FetchData -> fetchData()
            is MainScreenAction.OnClickItem -> viewEffect  =
                MainScreenEffect.OpenDetail(viewAction.binding, viewAction.realPropertyEntity)
        }
    }

    private fun fetchData(){
        viewState = viewState.copy(uiState = UiState.Fetching)
        viewModelScope.launch {
            delay(2000) //a short delay just to display loading view
            fetchDataUseCase(Unit).either({
                viewState = viewState.copy(uiState = UiState.Error(it))
            },{
                viewState = viewState.copy(uiState = UiState.Fetched, realPropertyList = it.map {realProperty -> entityMapper.toRealPropertyEntity(realProperty) })
            })
        }
    }

}