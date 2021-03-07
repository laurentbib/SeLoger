package com.biblublab.domain.common

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract  class UseCase <in Params, out FailureType, out SuccessType>(private val coroutineDispatcher: CoroutineDispatcher) {
    abstract suspend fun run(params: Params): Either<FailureType, SuccessType>

    suspend operator fun invoke(params: Params) : Either<FailureType, SuccessType> {
        return withContext(coroutineDispatcher){run(params)}
    }
}