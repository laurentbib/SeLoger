package com.biblublab.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RealPropertyApi {

    @GET("listings.json")
    suspend fun getRealPropertyList() : Response<RealPropertyResponse>

    @GET("listings/{listingId}.json")
    suspend fun getRealPropertyDetail(@Path("listingId")id : Int) : Response<RealPropertyDataResponse>
}