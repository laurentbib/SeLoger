package com.biblublab.domain.model

data class RealProperty(
    val id: Int,
    val bedRoomsNumber: Int,
    val city: String,
    val area: Int,
    val url: String?,
    val price: Int,
    val professional: String,
    val propertyType: String,
    val totalRooms: Int
) {
}