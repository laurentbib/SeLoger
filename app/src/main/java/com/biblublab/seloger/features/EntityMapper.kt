package com.biblublab.seloger.features

import android.content.Context
import com.biblublab.domain.model.RealProperty
import com.biblublab.seloger.R

class EntityMapper(private val context: Context) {

     fun toRealPropertyEntity(realProperty: RealProperty) = with(realProperty){
        RealPropertyEntity(id = id,
            url = url,
            type = propertyType,
            price = "$price €",
            otherInfosLabel = "$city | $area m2",
            bedRoomsInfosLabel = "${context.resources.getQuantityString(R.plurals.total_rooms_label, realProperty.totalRooms, realProperty.totalRooms)} | ${context.resources.getQuantityString(R.plurals.bedrooms_label, realProperty.bedRoomsNumber, realProperty.bedRoomsNumber)}",
            professionalLabel = professional
        )
    }
}