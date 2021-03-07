package com.biblublab.data.remote

import com.google.gson.annotations.SerializedName

data class RealPropertyResponse(
    val items: List<RealPropertyDataResponse>,
    val totalCount: Int
)

data class RealPropertyDataResponse(
    val id: Int,
    @SerializedName("bedrooms") val bedroomsNumber: Int,
    val city: String,
    val area: Int,
    val url: String?,
    val price: Int,
    val professional: String,
    val propertyType: String,
    @SerializedName("rooms") val totalRooms: Int
)