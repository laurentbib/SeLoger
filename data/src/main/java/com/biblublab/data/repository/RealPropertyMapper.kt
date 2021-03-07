package com.biblublab.data.repository

import com.biblublab.data.remote.RealPropertyDataResponse
import com.biblublab.domain.model.RealProperty

class RealPropertyMapper {

    fun toRealProperty(realPropertyDataResponse: RealPropertyDataResponse) : RealProperty = with(realPropertyDataResponse){
        RealProperty(id = id,
        bedRoomsNumber = bedroomsNumber,
        city = city,
        area = area,
        price = price,
        url = url,
        professional = professional,
        propertyType = propertyType,
        totalRooms = totalRooms)
    }
}