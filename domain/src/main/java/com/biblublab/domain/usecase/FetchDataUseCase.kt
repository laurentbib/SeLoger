package com.biblublab.domain.usecase

import com.biblublab.domain.common.AppCoroutineDispatchers
import com.biblublab.domain.common.Either
import com.biblublab.domain.common.UseCase
import com.biblublab.domain.model.RealProperty
import com.biblublab.domain.repository.RealPropertyRepository

class FetchDataUseCase(
    private val realPropertyRepository: RealPropertyRepository,
    private val appCoroutineDispatchers: AppCoroutineDispatchers) : UseCase<Unit, String, List<RealProperty>>(appCoroutineDispatchers.io) {

    override suspend fun run(params: Unit): Either<String, List<RealProperty>> =
        realPropertyRepository.fetchData()
}