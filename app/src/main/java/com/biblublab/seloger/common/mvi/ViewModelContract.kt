package com.biblublab.seloger.common.mvi

interface ViewModelContract<ACTION> {
    fun process(viewAction: ACTION)
}