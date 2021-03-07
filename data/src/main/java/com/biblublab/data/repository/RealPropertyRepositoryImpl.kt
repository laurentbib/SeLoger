package com.biblublab.data.repository

import com.biblublab.data.remote.RealPropertyApi
import com.biblublab.domain.common.Either
import com.biblublab.domain.model.RealProperty
import com.biblublab.domain.repository.RealPropertyRepository
import retrofit2.Response

class RealPropertyRepositoryImpl(private val realPropertyMapper: RealPropertyMapper,
                                 private val realPropertyApi: RealPropertyApi) : RealPropertyRepository{


    override suspend fun fetchData(): Either<String, List<RealProperty>> {
        return when (val networkCall = safeApiCall { realPropertyApi.getRealPropertyList() }){
            is Either.Failure -> Either.Failure(networkCall.fail)
            is Either.Successful -> Either.Successful(networkCall.success.items.map { realPropertyMapper.toRealProperty(it) })
        }
    }

    override suspend fun getDetails(id: Int): Either<String, RealProperty> {
        return when (val networkCall = safeApiCall { realPropertyApi.getRealPropertyDetail(id) }){
            is Either.Failure -> Either.Failure(networkCall.fail)
            is Either.Successful -> Either.Successful(realPropertyMapper.toRealProperty(networkCall.success))
        }
    }

    private suspend fun <T> safeApiCall(call: suspend () -> Response<T>): Either<String, T> {
        try {
            val response = call()
            if (response.isSuccessful && response.body() != null) {
                return Either.Successful(response.body()!!)
            }
            return error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): Either<String, T> =
        Either.Failure("Network call has failed because : $message")
}