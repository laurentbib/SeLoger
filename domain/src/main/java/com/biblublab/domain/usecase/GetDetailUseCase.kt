package com.biblublab.domain.usecase

import com.biblublab.domain.common.AppCoroutineDispatchers
import com.biblublab.domain.common.Either
import com.biblublab.domain.common.UseCase
import com.biblublab.domain.model.RealProperty
import com.biblublab.domain.repository.RealPropertyRepository

class GetDetailUseCase(private val realPropertyRepository: RealPropertyRepository,
                       private val appCoroutineDispatchers: AppCoroutineDispatchers) : UseCase<Int, String, RealProperty>(appCoroutineDispatchers.io) {

    override suspend fun run(params: Int): Either<String, RealProperty> = realPropertyRepository.getDetails(params)
}