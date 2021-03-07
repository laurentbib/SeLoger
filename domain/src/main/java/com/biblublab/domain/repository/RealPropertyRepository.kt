package com.biblublab.domain.repository

import com.biblublab.domain.common.Either
import com.biblublab.domain.model.RealProperty

interface RealPropertyRepository {

    suspend fun fetchData() : Either<String, List<RealProperty>>
    suspend fun getDetails(id : Int) : Either<String, RealProperty>
}